package com.ds.belong.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.api.client.UserClient;
import com.ds.belong.domain.po.Belong;
import com.ds.belong.mapper.BelongMapper;
import com.ds.belong.service.IBelongService;
import com.ds.common.domain.query.PageQuery;
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
     * @return 所属列表
     */
    @Override
    public Page<Belong> belongList(PageQuery pageQuery) {
        //TODO使用redis提高效率
        return lambdaQuery().page(pageQuery.toMpPage());
    }
}
