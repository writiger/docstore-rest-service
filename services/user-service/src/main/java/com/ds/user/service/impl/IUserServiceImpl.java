package com.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.common.exception.*;
import com.ds.common.utils.EMailTool;
import com.ds.user.config.JwtProperties;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.po.User;
import com.ds.user.domain.vo.UserLoginVo;
import com.ds.user.enums.UserStatus;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IUserService;
import com.ds.user.utils.JwtTool;
import com.ds.user.utils.VerifyTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    /**
     * @param loginDTO 登录用户信息
     * @return 用户VO
     */
    @Override
    public UserLoginVo login(LoginFormDTO loginDTO) {
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
        return new UserLoginVo(user,token);
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
     * @param email 注册邮箱
     * */
    @Override
    public void VCode(String email){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //1. 验证邮箱是否被使用
        Integer count = lambdaQuery().eq(User::getEmail,email).count();
        if(count != 0){
            throw new PreconditionFailed("邮箱已存在");
        }
        //2. 生成验证码
        EMailTool eMailTool = new EMailTool();
        String VCode = VerifyTool.getVCode();
        //3. 存入redis
        ops.set(email,VCode,120, TimeUnit.SECONDS);
        //4. 发送至邮箱
        eMailTool.sendVCodeEmail(VCode,email);
    }
}
