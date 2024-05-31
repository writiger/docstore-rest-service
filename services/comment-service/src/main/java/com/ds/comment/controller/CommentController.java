package com.ds.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.comment.domain.dto.UploadCommentDTO;
import com.ds.comment.domain.po.Comment;
import com.ds.comment.domain.vo.CommentVO;
import com.ds.comment.service.ICommentService;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ds.common.constants.Constants.AUTH_KEY;

/**
 * @author writiger
 * @description 评论控制
 * @create_at 2024-05-22 10:39
 */
@Api(tags = "评论相关接口")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @ApiOperation("文献评论列表接口")
    @GetMapping("/search/{uuid}")
    private R<PageDTO<CommentVO>> commentList(PageQuery pageQuery, @PathVariable(name = "uuid") String uuid){
        Page<CommentVO> commentList;
        try{
            commentList = commentService.getCommentList(pageQuery, uuid);
        }catch (Exception e){
            return R.error("Unknown Error");
        }
        return R.ok(PageDTO.of(commentList));
    }

    @ApiOperation("新增评论")
    @PostMapping("")
    private R<Void> uploadComment(@RequestHeader(AUTH_KEY)String userId,@RequestBody @Validated UploadCommentDTO uploadCommentDTO){
        try{
            commentService.uploadComment(userId,uploadCommentDTO);
        }catch (Exception e){
            return R.error("Unknown Error");
        }
        return R.ok();
    }
}
