package com.ds.comment.utils;

import com.ds.comment.domain.po.Comment;
import com.ds.comment.domain.vo.CommentVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 10:50
 */
public class CommentsTree {
    /**
     * 构建评论树
     * @param list
     * @return 父子整理后的评论
     */
    public static List<CommentVO> processComments(List<Comment> list) {
        Map<Long, Comment> map = new HashMap<>();   // (id, Comment)
        List<CommentVO> result = new ArrayList<>();
        // 将所有根评论加入 map
        for(Comment comment : list) {
            if(comment.getParentId() == null)
                result.add(new CommentVO(comment));
            map.put(comment.getId(), comment);
        }
        // 子评论加入到父评论的 child 中
        for(Comment comment : list) {
            Long id = comment.getParentId();
            if(id != null) {   // 当前评论为子评论
                CommentVO p = new CommentVO(map.get(id));
                if(p.getChild() == null)    // child 为空，则创建
                    p.setChild(new ArrayList<>());
                p.getChild().add(comment);
            }
        }
        return result;
    }
}
