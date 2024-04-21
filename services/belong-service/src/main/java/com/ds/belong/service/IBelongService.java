package com.ds.belong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.belong.domain.po.Belong;
import com.ds.common.domain.query.PageQuery;

public interface IBelongService extends IService<Belong> {
    Page<Belong> belongList(PageQuery pageQuery);
}
