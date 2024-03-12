package com.ds.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-12 16:08
 */
@Data
@ApiModel(description = "获取验证码表单实体")
public class VerifyFormDTO {
    @NotNull(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
}
