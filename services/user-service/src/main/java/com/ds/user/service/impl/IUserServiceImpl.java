package com.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.common.exception.*;
import com.ds.common.utils.ShortUUID;
import com.ds.user.config.JwtProperties;
import com.ds.user.domain.dto.ChangeFormDTO;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.po.User;
import com.ds.user.domain.vo.UserVo;
import com.ds.user.enums.UserLevel;
import com.ds.user.enums.UserStatus;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IUserService;
import com.ds.user.utils.JwtTool;
import com.ds.user.utils.VerifyEmail;
import com.ds.user.utils.VerifyPasswd;
import com.ds.user.utils.VerifyTool;
import feign.FeignException;
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

/**
 * @author writiger
 * @description
 * @create_at 2024-03-09 19:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

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
     * @return 用户VO
     */
    @Override
    public UserVo login(LoginFormDTO loginDTO) {
        //1. 数据校验
        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        //2. 查询用户数据
        User user = lambdaQuery().eq(User::getAccount, account).one();
        if(user==null){
            throw new RangeNotSatisfiable("该账号为空");
        }
        //3. 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        //4. 判断用户状态
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException("用户被封禁");
        }
        //5. 生成TOKEN
        String token = jwtTool.createToken(user.getId(), jwtProperties.getTokenTTL());
        //6. 返回VO
        return new UserVo(user,token);
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
        registerFormDTO.setPassword1(passwordEncoder.encode(registerFormDTO.getPassword1()));
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
     * @param token 用户令牌
     * @return 用户Vo
     */
    @Override
    public UserVo infoByToken(String token){
        //1. 解析token
        Long id = jwtTool.parseToken(token);
        //2. 使用id查询用户
        User user = lambdaQuery().eq(User::getId,id).one();
        //3. 返回
        return new UserVo(user,token);
    }

    /**
     * @param token 用户令牌
     */
    @Override
    public void removeByToken(String token) {
        //1. 解析token
        Long id = jwtTool.parseToken(token);
        //2. 禁止管理员注销自己
        User user = lambdaQuery().eq(User::getId,id).one();
        if(!user.getLevel().equals(UserLevel.NORMAL)){
            throw new ForbiddenException("管理员无权注销自己");
        }
        //3. 删除用户
        userMapper.deleteById(id);
    }

    /**
     * @param changeFormDTO 修改个人信息表单
     * @param token token字符串
     * @return 修改后的用户信息
     */
    @Override
    public UserVo changeInfoByToken(ChangeFormDTO changeFormDTO, String token) {
        //1. 验证密码一致性
        if(!Objects.equals(changeFormDTO.getPassword1(), changeFormDTO.getPassword2())){
            throw new PreconditionFailed("密码不一致");
        }
        //2. 解析token
        Long id = jwtTool.parseToken(token);
        //3. 通过token查询用户
        User user = lambdaQuery().eq(User::getId,id).one();
        //4. 验证用户否匹配
        if(!user.getAccount().equals(changeFormDTO.getAccount())){
            throw new PreconditionFailed("个人信息修改失败");
        }
        //5. 修改个人信息
        user.setName(changeFormDTO.getName());
        if(!Objects.equals(changeFormDTO.getPassword1(), "")){
            user.setPassword(passwordEncoder.encode(changeFormDTO.getPassword1()));
        }
        user.setAvatar(changeFormDTO.getAvatar());
        userMapper.updateById(user);
        //6. 返回修改后的信息
        return new UserVo(user,token);
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
        // 2. 生成验证码
        String verifyCode = RandomStringUtils.randomAlphanumeric(12);
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
}
