package com.ds.comment.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.api.client.UserClient;
import com.ds.comment.domain.dto.UploadCommentDTO;
import com.ds.comment.domain.po.Comment;
import com.ds.comment.domain.vo.CommentVO;
import com.ds.comment.mapper.CommentMapper;
import com.ds.comment.service.ICommentService;
import com.ds.common.domain.R;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.domain.vo.UserVo;
import com.ds.common.enums.CommentDelete;
import com.ds.common.utils.ShortUUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ds.comment.domain.vo.CommentVO.ToVOList;
import static com.ds.comment.utils.CommentsTree.processComments;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 10:42
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ICommentServiceImpl  extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    private final UserClient userClient;

    private final CommentMapper commentMapper;

    @Override
    public Page<CommentVO> getCommentList(PageQuery pageQuery, String uuid){
        Page<CommentVO> res = new Page<CommentVO>();
        //1. 获取根评论
        Page<Comment> pageRoot = lambdaQuery().eq(Comment::getDocUuid,uuid).eq(Comment::getRootParentId,0).page(pageQuery.toMpPage());
        List<CommentVO> rootList = ToVOList(pageRoot.getRecords());
        //2. 获取子评论
        for (CommentVO commentVO : rootList) {
            Page<Comment> pageChild = lambdaQuery().eq(Comment::getRootParentId,commentVO.getId())
                    .page(new Page<>(1,5));
            commentVO.setChild(pageChild.getRecords());
            commentVO.setChildTotal((int) pageChild.getTotal());
        }
        res.setTotal(pageRoot.getTotal());
        res.setRecords(rootList);
        res.setPages(pageRoot.getPages());
        return res;
    }

    @Override
    public Void uploadComment(String userId, UploadCommentDTO uploadCommentDTO) {
        //1. 获取评论者信息
        R<UserVo> infoV = userClient.info(userId);
        UserVo userVo = infoV.getData();
        //2. 整合评论信息
        Comment comment = new Comment();
        comment.setContent(uploadCommentDTO.getContent());
        comment.setUserId(Long.valueOf(userId));
        comment.setDocUuid(uploadCommentDTO.getUuid());
        comment.setIsDelete(CommentDelete.EXIST);
        comment.setParentId(uploadCommentDTO.getTo());
        comment.setRootParentId(uploadCommentDTO.getRoot());
        comment.setUserName(userVo.getName());
        comment.setCreateTime(new Date());
        //3. 保存
        commentMapper.insert(comment);
        return null;
    }
}
