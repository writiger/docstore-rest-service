package com.ds.doc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.doc.domain.dto.UploadDocDTO;
import com.ds.doc.domain.po.Doc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IDocService extends IService<Doc> {
    void uploadDoc(UploadDocDTO uploadDocDTO, MultipartFile file);
}
