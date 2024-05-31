package com.ds.doc.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-21 09:24
 */
@Data
@ApiModel(description = "基础检索表单实体")
public class BasicSearchDTO {
    @NotNull
    @ApiModelProperty(value = "pageNo",required = true)
    private int pageNo;

    @NotNull
    @ApiModelProperty(value = "pageSize",required = true)
    private int pageSize;

    @NotNull(message = "数据库不能为空")
    @ApiModelProperty(value = "belong",required = true)
    private String belong;

    @NotNull(message = "检索条件不能为空")
    @ApiModelProperty(value = "item",required = true)
    private BasicItem[] items;


    @Data
    public static
    class BasicItem{
        private String key;
        private String linkWay;
        private String value;
    }
}
