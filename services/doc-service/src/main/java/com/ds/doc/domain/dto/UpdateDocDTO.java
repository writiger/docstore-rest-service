package com.ds.doc.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-16 15:29
 */
@Data
@ApiModel(description = "更新文献表单实体")
public class UpdateDocDTO {
    @NotNull(message = "作者名不能为空")
    @ApiModelProperty(value = "author",required = true)
    private String author;

    @NotNull(message = "摘要不能为空")
    @ApiModelProperty(value = "digest",required=true)
    private String digest;

    @NotNull(message = "关键词不能为空")
    @ApiModelProperty(value = "关键词",required = true)
    private String keyword;

    @NotNull(message="uuid不能为空")
    @ApiModelProperty(value="uuid",required = true)
    private String uuid;
}
