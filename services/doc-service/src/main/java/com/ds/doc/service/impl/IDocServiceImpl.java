package com.ds.doc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.doc.domain.po.Doc;
import com.ds.doc.mapper.DocMapper;
import com.ds.doc.service.IDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author writiger
 * @description
 * @create_at 2024-04-27 13:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IDocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {
}
