package com.ds.doc.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author writiger
 * @description 文献持久层类类
 * @create_at 2024-04-27 13:11
 */
@Data
@Accessors(chain = true)
@TableName("doc")
public class Doc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 主题
     */
    private String theme;

    /**
     * 作者（用分号分割）
     */
    private String author;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 关键词（使用分号分割）
     */
    private String keyword;

    /**
     * 文件后缀
     */
    private String suffix;
}
