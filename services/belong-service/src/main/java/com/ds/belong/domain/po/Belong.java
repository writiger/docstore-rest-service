package com.ds.belong.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author writiger
 * @description 所属持久类
 * @create_at 2024-04-20 13:56
 */
@Data
@Accessors(chain = true)
@TableName("belong")
public class Belong implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
