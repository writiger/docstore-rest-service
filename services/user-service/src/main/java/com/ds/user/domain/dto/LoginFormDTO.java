package com.ds.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author writiger
 * @description
 * @create_at 2024-03-09 17:23
 */
@Data
@ApiModel(description = "登录表单实体")
public class LoginFormDTO {

    @NotNull(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    // TODO
    @ApiModelProperty(value = "是否记住我", required = false)
    private Boolean rememberMe = false;
}
