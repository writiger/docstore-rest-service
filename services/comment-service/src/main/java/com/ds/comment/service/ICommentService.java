package com.ds.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.comment.domain.dto.UploadCommentDTO;
import com.ds.comment.domain.po.Comment;
import com.ds.comment.domain.vo.CommentVO;
import com.ds.common.domain.query.PageQuery;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 10:41
 */
public interface ICommentService  extends IService<Comment> {
    Page<CommentVO> getCommentList(PageQuery pageQuery, String uuid);

    Void uploadComment(String userId, UploadCommentDTO uploadCommentDTO);
}
