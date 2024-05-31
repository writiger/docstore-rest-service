package com.ds.doc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import com.ds.doc.domain.dto.BasicSearchDTO;
import com.ds.doc.domain.dto.UpdateDocDTO;
import com.ds.doc.domain.dto.UploadDocDTO;
import com.ds.doc.domain.po.Doc;
import com.ds.doc.service.IDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.ds.common.constants.Constants.AUTH_KEY;

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
    //从配置文件中读取当前服务所属
    @Value("${spring.cloud.nacos.discovery.metadata.belong}")
    private String belong;

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

    @ApiOperation("基础查询文献")
    @PostMapping("/search/basic")
    public R<PageDTO<Doc>> basicSearch(@RequestBody @Validated BasicSearchDTO basicSearchDTO){
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(basicSearchDTO.getPageSize());
        pageQuery.setPageNo(basicSearchDTO.getPageNo());
        Page<Doc> docList;
        try{
            docList = docService.basicSearch(pageQuery,basicSearchDTO);
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

    @ApiOperation("通过uuid查找文献")
    @GetMapping("/search/{uuid}")
    public R<Doc> findByUuid(@PathVariable("uuid") String uuid){
        Doc doc;
        try{
            doc = docService.lambdaQuery().eq(Doc::getUuid,uuid).one();
        }catch (CommonException e) {
            // 返回自定义错误
            return R.error(e);
        }
        return R.ok(doc);
    }

    @ApiOperation("管理文献列表")
    @GetMapping("/manage/list")
    public R<PageDTO<Doc>> manageList(PageQuery pageQuery){
        System.out.println("belong:"+belong);
        Page<Doc> docList;
        try{
            docList = docService.lambdaQuery()
                    .eq(Doc::getBelong,belong)
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

    @ApiOperation("模糊文献管理列表")
    @GetMapping("/manage/like/{col}")
    public R<PageDTO<Doc>> manageListLike(PageQuery pageQuery,@PathVariable("col") String col){
        Page<Doc> docList;
        try{
            docList = docService.manageList(pageQuery,col,belong);
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

    @ApiOperation("修改文献")
    @PutMapping("")
    public R<Void> changeByUuid(@RequestHeader(AUTH_KEY)String userId,@RequestBody @Validated UpdateDocDTO updateDocDTO){
        try{
            docService.changeByUuid(userId, updateDocDTO);
        }catch (CommonException e) {
            // 返回自定义错误
            return R.error(e);
        }
        return R.ok();
    }

    @ApiOperation("上传文献")
    @PostMapping(value="/upload",consumes = {"*/*"})
    public R<Void> upload(@RequestHeader(AUTH_KEY)String userId,UploadDocDTO uploadDocDTO,@RequestParam("file") MultipartFile file){
        docService.uploadDoc(userId,uploadDocDTO,file);
        return R.ok();
    }

    @ApiOperation("删除文献")
    @DeleteMapping("/{uuid}")
    public R<Void> delete(@RequestHeader(AUTH_KEY)String userId,@PathVariable("uuid") String uuid){
        try{
            docService.deleteDoc(userId,uuid);
        }catch (CommonException e) {
            // 返回自定义错误
            return R.error(e);
        }
        return R.ok();
    }
}
