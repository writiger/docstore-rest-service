package com.ds.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.common.enums.UserLevel;
import com.ds.user.domain.dto.ChangeFormDTO;
import com.ds.user.domain.dto.ChangePasswdFormDTO;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.po.User;
import com.ds.common.domain.vo.UserVo;
import com.ds.user.domain.vo.UserLoginVO;

public interface IUserService extends IService<User> {
    UserLoginVO getPasswd(LoginFormDTO loginFormDTO);

    void register(RegisterFormDTO registerFormDTO);

    void verify(String email);

    UserVo infoByToken(String userId);

    void removeByToken(String token);

    UserVo changeInfoByToken(ChangeFormDTO changeFormDTO,String token);

    void verifyPasswd(String email);

    void changePasswd(ChangePasswdFormDTO changePasswdFormDTO,String verifyCode);

    UserLevel getLevel(Long userId);
}
