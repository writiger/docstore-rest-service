package com.ds.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.po.User;
import com.ds.user.domain.vo.UserVo;

public interface IUserService extends IService<User> {
    UserVo login(LoginFormDTO loginFormDTO);

    void register(RegisterFormDTO registerFormDTO);

    void verify(String email);

    UserVo infoByToken(String token);
}
