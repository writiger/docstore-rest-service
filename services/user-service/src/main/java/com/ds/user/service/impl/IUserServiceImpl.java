package com.ds.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.common.exception.AccountIsEmptyException;
import com.ds.common.exception.BadRequestException;
import com.ds.common.exception.ForbiddenException;
import com.ds.user.config.JwtProperties;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.po.User;
import com.ds.user.domain.vo.UserLoginVo;
import com.ds.user.enums.UserStatus;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IUserService;
import com.ds.user.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public UserLoginVo login(LoginFormDTO loginDTO) {
        //1. 数据校验
        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        //2. 查询用户数据
        User user = lambdaQuery().eq(User::getAccount, account).one();
        if(user==null){
            throw new AccountIsEmptyException("该账号为空");
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
}
