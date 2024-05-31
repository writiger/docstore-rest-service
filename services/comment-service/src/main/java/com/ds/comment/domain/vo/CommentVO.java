package com.ds.comment.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ds.comment.domain.po.Comment;
import com.ds.common.enums.CommentDelete;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 12:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private Date createTime;
    private CommentDelete isDelete;
    private String docUuid;
    private Long parentId;
    private Long rootParentId;
    private List<Comment> child;
    private int childTotal;

    public CommentVO(Comment comment){
        id = comment.getId();
        content = comment.getContent();
        userId = comment.getUserId();
        userName = comment.getUserName();
        createTime = comment.getCreateTime();
        isDelete = comment.getIsDelete();
        docUuid = comment.getDocUuid();
        parentId = comment.getParentId();
        rootParentId = comment.getRootParentId();
    }

    public static List<CommentVO> ToVOList(List<Comment> list){
        List<CommentVO> res = new ArrayList<>();
        for (Comment comment : list) {
            res.add(new CommentVO(comment));
        }
        return res;
    }
}
