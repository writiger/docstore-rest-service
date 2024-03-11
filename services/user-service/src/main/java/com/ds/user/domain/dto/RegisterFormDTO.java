package com.ds.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-11 13:21
 */
@Data
@ApiModel(description = "注册表单实体")
public class RegisterFormDTO {

    @NotNull(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password1;

    @NotNull(message = "重复密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password2;

    @NotNull(message = "所属不能为空             ")
}
