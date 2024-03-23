package com.ds.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.vo.UserVo;
import com.ds.user.domain.dto.ChangeFormDTO;
import com.ds.user.domain.po.User;

import java.util.List;

public interface IAdminService extends IService<User> {
    void changeInfoByToken(ChangeFormDTO changeFormDTO);
}
