package com.ds.doc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.api.client.FileClient;
import com.ds.api.client.UserClient;
import com.ds.common.domain.R;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.domain.vo.UserVo;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.ForbiddenException;
import com.ds.common.utils.ShortUUID;
import com.ds.doc.domain.dto.BasicSearchDTO;
import com.ds.doc.domain.dto.UpdateDocDTO;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private final UserClient userClient;

    @Transactional
    @Override
    public void uploadDoc(String userId,UploadDocDTO uploadDocDTO, MultipartFile file) {
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
        //2.1. 获取文献所属
        R<UserVo> infoV = userClient.info(userId);
        UserVo userVo = infoV.getData();
        doc.setBelong(userVo.getBelong());
        docMapper.insert(doc);
        //3. 保存文件
        fileClient.uploadDoc(file,uuid);
    }

    @Override
    @Transactional
    public void deleteDoc(String userId, String uuid) {
        //1. 查询请求发起者身份
        R<UserVo> userR = userClient.info(userId);
        UserVo userVo = userR.getData();
        if(userVo.getLevel() != UserLevel.ADMIN){
            throw new ForbiddenException("only admin");
        }
        //2. 查询文献
        Doc doc = lambdaQuery().eq(Doc::getUuid, uuid).one();
        //3. 验证信息
        if(!doc.getBelong().equals(userVo.getBelong())){
            throw new ForbiddenException("wrong belong");
        }
        //4.1. 删除数据库文献
        docMapper.deleteById(doc);
        //4.2. 删除本地文件
        fileClient.delete(uuid,doc.getSuffix());
    }

    @Override
    public void changeByUuid(String userId, UpdateDocDTO updateDocDTO) {
        //1. 获取用户信息
        R<UserVo> userR = userClient.info(userId);
        UserVo user = userR.getData();
        if(user.getLevel()!= UserLevel.ADMIN){
            throw new ForbiddenException("Insufficient authority");
        }
        //2. 验证信息
        //TODO 区域验证
        Doc doc = lambdaQuery().eq(Doc::getUuid, updateDocDTO.getUuid()).one();
        doc.setAuthor(updateDocDTO.getAuthor());
        doc.setDigest(updateDocDTO.getDigest());
        doc.setKeyword(updateDocDTO.getKeyword());
        //3. 更新文献
        docMapper.updateById(doc);
    }

    @Override
    public Page<Doc> basicSearch(PageQuery pageQuery, BasicSearchDTO basicSearchDTO){
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<Doc>();
        BasicSearchDTO.BasicItem[] items = basicSearchDTO.getItems();
        for (BasicSearchDTO.BasicItem item : items) {
            if(Objects.equals(item.getLinkWay(), "and")) {
                queryWrapper.like(item.getKey(), item.getValue());
            }else {
                queryWrapper.or().like(item.getKey(),item.getValue());
            }
        }

        if(!Objects.equals(basicSearchDTO.getBelong(), "all")){
            queryWrapper.eq("belong",basicSearchDTO.getBelong());
        }

        return docMapper.selectPage(pageQuery.toMpPage(), queryWrapper);
    }

    @Override
    public Page<Doc> manageList(PageQuery pageQuery,String col,String belong){
        QueryWrapper<Doc> docQueryWrapper = new QueryWrapper<Doc>().eq("belong",belong);
        docQueryWrapper.and(i-> i
                .or()
                .like("theme",col)
                .or()
                .like("author",col)
                .or()
                .like("digest",col)
                .or()
                .like("uuid",col)
                .or()
                .like("id",col)
        );
        return docMapper.selectPage(pageQuery.toMpPage(),docQueryWrapper);
    }
}
