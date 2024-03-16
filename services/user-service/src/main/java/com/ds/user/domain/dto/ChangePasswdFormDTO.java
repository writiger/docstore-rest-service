package com.ds.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description 使用验证码修改密码
 * @create_at 2024-03-16 15:06
 */
@Data
@ApiModel(description = "使用验证码修改密码")
public class ChangePasswdFormDTO {

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password1;

    @NotNull(message = "重复密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password2;

    @NotNull(message = "邮箱不能为空")
    @ApiModelProperty(value="邮箱",required = true)
    private String email;
}
