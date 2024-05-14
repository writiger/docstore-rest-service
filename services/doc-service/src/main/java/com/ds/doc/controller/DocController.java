package com.ds.doc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import com.ds.doc.domain.dto.UploadDocDTO;
import com.ds.doc.domain.po.Doc;
import com.ds.doc.service.IDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author writiger
 * @description 文献接口
 * @create_at 2024-04-27 13:29
 */
@Api(tags = "文献相关接口")
@RestController
@RequestMapping("/doc")
@RequiredArgsConstructor
public class DocController {

    private final static Logger logger = LoggerFactory.getLogger(DocController.class);

    private final IDocService docService;

    @ApiOperation("查询文献列表")
    @GetMapping("/search/list")
    public R<PageDTO<Doc>> docListNor(PageQuery pageQuery){
        Page<Doc> docList;
        try{
            docList = docService.lambdaQuery()
                    .page(pageQuery.toMpPage());
        }catch (CommonException e){
            // 返回自定义错误
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(PageDTO.of(docList));
    }

    @ApiOperation("简单查询文献")
    @GetMapping("/search/easy/{col}")
    public R<PageDTO<Doc>> easySearch(PageQuery pageQuery,@PathVariable("col") String col){
        Page<Doc> docList;
        try{
            docList = docService.lambdaQuery()
                    .like(Doc::getTheme,col)
                    .or()
                    .like(Doc::getAuthor,col)
                    .or()
                    .like(Doc::getDigest,col)
                    .or()
                    .like(Doc::getUuid,col)
                    .or()
                    .like(Doc::getId,col)
                    .or()
                    .page(pageQuery.toMpPage());
        }catch (CommonException e){
            // 返回自定义错误
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(PageDTO.of(docList));
    }

    @ApiOperation("上传文献")
    @PostMapping(value="/upload",consumes = {"*/*"})
    public R<Void> upload(UploadDocDTO uploadDocDTO,@RequestParam("file") MultipartFile file){
        docService.uploadDoc(uploadDocDTO,file);
        return R.ok();
    }
}
