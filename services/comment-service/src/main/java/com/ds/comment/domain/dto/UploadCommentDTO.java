package com.ds.comment.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 14:47
 */
@Data
@ApiModel(description = "上传评论表单实体")
public class UploadCommentDTO {
    @NotNull(message = "文本内容不能为空")
    @ApiModelProperty(value = "content",required = true)
    private String content;

    @NotNull(message = "评论所属文献不能为空")
    @ApiModelProperty(value = "uuid",required = true)
    private String uuid;

    @NotNull(message = "回复对象id不能为空")
    @ApiModelProperty(value = "to",required = true)
    private Long to;

    @NotNull(message = "根评论不能为空")
    @ApiModelProperty(value="root",required = true)
    private Long root;

}
