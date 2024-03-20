package com.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.vo.UserVo;
import com.ds.user.domain.po.User;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 16:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IAdminServiceImpl extends ServiceImpl<UserMapper, User>implements IAdminService {

    private final UserMapper userMapper;
}
