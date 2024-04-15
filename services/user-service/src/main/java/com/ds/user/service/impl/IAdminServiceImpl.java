package com.ds.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.vo.UserVo;
import com.ds.common.enums.UserLevel;
import com.ds.common.enums.UserStatus;
import com.ds.common.exception.CommonException;
import com.ds.common.exception.PreconditionFailed;
import com.ds.user.domain.dto.ChangeFormDTO;
import com.ds.user.domain.po.User;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IAdminService;
import kotlin.collections.LongIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    /**
     * @param changeFormDTO 修改用户信息表单
     */
    @Override
    public void changeInfoByToken(ChangeFormDTO changeFormDTO) {
        //1. 验证表单信息
        User user = lambdaQuery().eq(User::getId, changeFormDTO.getId()).one();
        if(user.getLevel()!= UserLevel.NORMAL){
            throw new PreconditionFailed("无法修改管理员信息");
        }

        //2. 保存修改后的信息
        user.setName(changeFormDTO.getName());
        user.setAvatar(changeFormDTO.getAvatar());
        userMapper.updateById(user);
    }

    /**
     * @param account 账号
     */
    @Override
    public void ban(String account){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getAccount,account).eq(User::getLevel,UserLevel.NORMAL).set(User::getStatus,UserStatus.FROZEN);
        userMapper.update(null,updateWrapper);
    }

    /**
     * @param account 账号
     */
    @Override
    public void unban(String account){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getAccount,account).eq(User::getLevel,UserLevel.NORMAL).set(User::getStatus, UserStatus.NORMAL);
        userMapper.update(null,updateWrapper);
    }

    /**
     * @param account 待修改用户
     * @param userIdStr 调用者
     */
    @Override
    public void add(String account,String userIdStr){
        Long userId = Long.parseLong(userIdStr);
        User user = lambdaQuery().eq(User::getId,userId).one();
        if (!UserLevel.ROOT.equals(user.getLevel())){
            throw new PreconditionFailed("仅超级管理员可用");
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getAccount,account).eq(User::getLevel,UserLevel.NORMAL).set(User::getLevel,UserLevel.ADMIN);
        userMapper.update(null,updateWrapper);
    }
}


