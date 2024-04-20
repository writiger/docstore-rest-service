package com.ds.belong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.belong.domain.po.Belong;
import com.ds.belong.mapper.BelongMapper;
import com.ds.belong.service.IBelongService;
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
}
