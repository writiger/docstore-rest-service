package com.ds.belong.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.api.client.UserClient;
import com.ds.belong.domain.po.Belong;
import com.ds.belong.mapper.BelongMapper;
import com.ds.belong.service.IBelongService;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author writiger
 * @description
 * @create_at 2024-04-20 13:53
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IBelongServiceImpl extends ServiceImpl<BelongMapper, Belong> implements IBelongService {

    private final UserClient userClient;

    /**
     * @param pageQuery 分页参数
     * @param userId 用户ID
     * @return 所属列表
     */
    @Override
    public Page<Belong> belongList(PageQuery pageQuery,Long userId) {
        //1. 验证是否为root
        UserLevel userLevel = userClient.getLevel(userId).getData();
        if(userLevel != UserLevel.ROOT){
            throw new ForbiddenException("仅超级管理员可查看");
        }
        //2. 返回错误
        return lambdaQuery().page(pageQuery.toMpPage());
    }
}
