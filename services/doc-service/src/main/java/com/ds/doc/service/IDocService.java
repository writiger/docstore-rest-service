package com.ds.doc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.common.domain.query.PageQuery;
import com.ds.doc.domain.dto.BasicSearchDTO;
import com.ds.doc.domain.dto.UpdateDocDTO;
import com.ds.doc.domain.dto.UploadDocDTO;
import com.ds.doc.domain.po.Doc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IDocService extends IService<Doc> {
    void uploadDoc(String userId,UploadDocDTO uploadDocDTO, MultipartFile file);

    void deleteDoc(String userId,String uuid);

    void changeByUuid(String userId, UpdateDocDTO updateDocDTO);

    Page<Doc> basicSearch(PageQuery pageQuery, BasicSearchDTO basicSearchDTO);

    Page<Doc> manageList(PageQuery pageQuery,String col,String belong);
}
