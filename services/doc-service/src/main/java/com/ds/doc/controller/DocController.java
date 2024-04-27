package com.ds.doc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import com.ds.doc.domain.po.Doc;
import com.ds.doc.service.IDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/search/normal")
    public R<PageDTO<Doc>> docListNor(@RequestParam(name = "key") String key,PageQuery pageQuery){
        Page<Doc> docList;
        try{
            docList = docService.lambdaQuery()
//                    .like(Doc::getTheme,key)
//                    .or()
//                    .like(Doc::getAuthor,key)
//                    .or()
//                    .like(Doc::getDigest,key)
//                    .or()
//                    .like(Doc::getKeyword,key)
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
}
