package com.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.common.exception.*;
import com.ds.user.domain.dto.ChangeFormDTO;
import com.ds.user.domain.dto.ChangePasswdFormDTO;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.po.User;
import com.ds.common.domain.vo.UserVo;
import com.ds.common.enums.UserLevel;
import com.ds.common.enums.UserStatus;
import com.ds.user.domain.vo.UserLoginVO;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IUserService;
import com.ds.user.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.ds.user.utils.UserPO2VO.PO2VO;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-09 19:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final StringRedisTemplate stringRedisTemplate;

    private final UserMapper userMapper;

    // 此处错误不影响运行
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${ds.web.url}")
    private String url;

    /**
     * @param loginDTO 登录用户信息
     * @return 数据库中保存的密码
     */
    @Override
    public UserLoginVO getPasswd(LoginFormDTO loginDTO) {
        UserLoginVO userLoginVO = new UserLoginVO();
        //1. 数据校验
        String account = loginDTO.getAccount();
        //2. 查询用户数据
        User user = lambdaQuery().eq(User::getAccount, account).one();
        if(user==null){
            throw new RangeNotSatisfiable("该账号为空");
        }
        //4. 判断用户状态
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException("用户被封禁");
        }
        //6. 返回String
        userLoginVO.setId(user.getId());
        userLoginVO.setInPasswd(loginDTO.getPassword());
        userLoginVO.setOutPasswd(user.getPassword());
        return userLoginVO;
    }

    /**
     * @param registerFormDTO 注册用户信息
     */
    @Override
    public void register(RegisterFormDTO registerFormDTO){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //1. 验证账号是否为空
        Integer count = lambdaQuery().eq(User::getAccount, registerFormDTO.getAccount()).count();
        if(count != 0){
            throw new PreconditionFailed("账号已存在");
        }
        //2. 验证密码是否匹配
        if(!Objects.equals(registerFormDTO.getPassword1(), registerFormDTO.getPassword2())){
            throw new PreconditionFailed("密码不匹配");
        }
        // 密码验证通过加密密码
        registerFormDTO.setPassword1(registerFormDTO.getPassword1());
        //3. 验证单位是否存在
        //TODO 调用belong服务
        //4. 验证验证码是否和邮箱匹配
        String v = ops.get(registerFormDTO.getEmail());
        if(!Objects.equals(v, registerFormDTO.getVerify()) || v == null){
            throw new PreconditionFailed("验证码失效或错误");
        }
        //5. 存入数据库
        User user = RegisterFormDTO.converseTOUser(registerFormDTO);
        userMapper.insert(user);
    }

    /**
     * @param email 注册人的邮箱
     */
    @Override
    public void verify(String email){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //1. 验证邮箱是否已被使用
        Integer count = lambdaQuery().eq(User::getEmail, email).count();
        if(count != 0){
            throw new PreconditionFailed("邮箱已被使用");
        }
        //2. 生成验证码
        String verifyCode = VerifyTool.generateCode();
        //3. 存于redis
        ops.set(email,verifyCode,120, TimeUnit.SECONDS);
        //4. 发送邮件
        //创建简单邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        VerifyEmail verifyEmail = new VerifyEmail(email,verifyCode);
        try{
            MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
            minehelper.setFrom(from);
            minehelper.setTo(verifyEmail.getTos());
            minehelper.setSubject(verifyEmail.getSubject());
            minehelper.setText(verifyEmail.getContent(),true);
            mailSender.send(message);
        }catch (MessagingException me){
            throw new BadRequestException("服务器错误，请联系管理员");
        }
    }

    /**
     * @param userId 用户id
     * @return 用户Vo
     */
    @Override
    public UserVo infoByToken(String userId){
        return PO2VO(lambdaQuery().eq(User::getId,userId).one());
    }

    /**
     * @param userId 从用户token中解析出的id
     */
    @Override
    public void removeByToken(String userId) {
        //2. 禁止管理员注销自己
        User user = lambdaQuery().eq(User::getId,userId).one();
        if(!user.getLevel().equals(UserLevel.NORMAL)){
            throw new ForbiddenException("管理员无权注销自己");
        }
        //3. 删除用户
        userMapper.deleteById(user.getId());
    }

    /**
     * @param changeFormDTO 修改个人信息表单
     * @param userId 从用户token中解析出的id
     * @return 修改后的用户信息
     */
    @Override
    public UserVo changeInfoByToken(ChangeFormDTO changeFormDTO, String userId) {
        //1. 验证密码一致性
        if(!Objects.equals(changeFormDTO.getPassword1(), changeFormDTO.getPassword2())){
            throw new PreconditionFailed("密码不一致");
        }
        //3. 通过token查询用户
        User user = lambdaQuery().eq(User::getId,userId).one();
        //4. 验证用户否匹配
        if(!user.getAccount().equals(changeFormDTO.getAccount())){
            throw new PreconditionFailed("个人信息修改失败");
        }
        //5. 修改个人信息
        user.setName(changeFormDTO.getName());
        if(!Objects.equals(changeFormDTO.getPassword1(), "")){
            user.setPassword(changeFormDTO.getPassword1());
        }
        user.setAvatar(changeFormDTO.getAvatar());
        userMapper.updateById(user);
        //6. 返回修改后的信息
        return PO2VO(user);
    }

    /**
     * @param email 需要修改密码的邮箱
     */
    @Override
    public void verifyPasswd(String email) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        // 1. 验证邮箱是否存在
        Integer count = lambdaQuery().eq(User::getEmail, email).count();
        if(count == 0){
            throw new PreconditionFailed("邮箱并未注册");
        }
        // 2. 生成验证码 8位足够用
        String verifyCode = RandomStringUtils.randomAlphanumeric(8);
        // 3. 存储验证码 10分钟
        ops.set("pd"+email,verifyCode,600,TimeUnit.SECONDS);
        // 4. 发送验证码
        //创建简单邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        VerifyPasswd verifyPasswd = new VerifyPasswd(email,url,verifyCode);
        try{
            MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
            minehelper.setFrom(from);
            minehelper.setTo(verifyPasswd.getTos());
            minehelper.setSubject(verifyPasswd.getSubject());
            minehelper.setText(verifyPasswd.getContent(),true);
            mailSender.send(message);
        }catch (MessagingException me){
            throw new BadRequestException("服务器错误，请联系管理员");
        }
    }

    /**
     * @param changePasswdFormDTO 根据验证码修改密码DTO
     */
    @Override
    public void changePasswd(ChangePasswdFormDTO changePasswdFormDTO,String verifyCode) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //1. 验证密码是否匹配
        if(!Objects.equals(changePasswdFormDTO.getPassword1(), changePasswdFormDTO.getPassword2())){
            throw new PreconditionFailed("密码不匹配");
        }
        //2. 验证验证码
        String v = ops.get("pd"+changePasswdFormDTO.getEmail());
        if(!Objects.equals(v, verifyCode) || v == null){
            throw new PreconditionFailed("验证码失效或错误");
        }
        //3. 修改密码
        User user = lambdaQuery().eq(User::getEmail,changePasswdFormDTO.getEmail()).one();
        user.setPassword(changePasswdFormDTO.getPassword1());
        userMapper.updateById(user);
    }
}
