package com.ds.doc.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-13 19:06
 */
@Data
@ApiModel(description = "上传文献实体")
public class UploadDocDTO {

    @NotNull(message = "文献主题不能为空")
    @ApiModelProperty(value = "主题", required = true)
    private String theme;

    @NotNull(message = "作者名不能为空")
    @ApiModelProperty(value = "author",required = true)
    private String author;

    @NotNull(message = "摘要不能为空")
    @ApiModelProperty(value = "digest",required=true)
    private String digest;

    @NotNull(message = "关键词不能为空")
    @ApiModelProperty(value = "关键词",required = true)
    private String keyword;
}
