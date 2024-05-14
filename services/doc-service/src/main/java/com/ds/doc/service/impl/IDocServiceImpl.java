package com.ds.doc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.api.client.FileClient;
import com.ds.common.utils.ShortUUID;
import com.ds.doc.domain.dto.UploadDocDTO;
import com.ds.doc.domain.po.Doc;
import com.ds.doc.mapper.DocMapper;
import com.ds.doc.service.IDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.File;

/**
 * @author writiger
 * @description
 * @create_at 2024-04-27 13:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IDocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {

    private final DocMapper docMapper;

    private final FileClient fileClient;

    @Transactional
    @Override
    public void uploadDoc(UploadDocDTO uploadDocDTO, MultipartFile file) {
        //1. 生成uuid
        String uuid = ShortUUID.generateShortUUID();
        //2. 保存文献信息
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Doc doc = new Doc();
        doc.setAuthor(uploadDocDTO.getAuthor());
        doc.setTheme(uploadDocDTO.getTheme());
        doc.setUuid(uuid);
        doc.setDigest(uploadDocDTO.getDigest());
        doc.setKeyword(uploadDocDTO.getKeyword());
        doc.setSuffix(fileSuffix);
        docMapper.insert(doc);
        //3. 保存文件
        fileClient.uploadDoc(file,uuid);
    }
}
