package com.ds.comment.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ds.common.enums.CommentDelete;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 10:24
 */
@Data
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论作者ID
     */
    private Long userId;

    /**
     * 评论作者用户名
     */
    private String userName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 是否删除(0-未删除,1-已删除)
     */
    private CommentDelete isDelete;

    /**
     * 文献UUID
     */
    private String docUuid;

    /**
     * 父评论ID 被回复的评论
     */
    private Long parentId;

    /**
     * 根评论ID
     */
    private Long rootParentId;
}
