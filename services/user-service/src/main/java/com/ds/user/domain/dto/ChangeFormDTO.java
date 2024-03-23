package com.ds.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author writiger
 * @description 修改个人信息DTO
 * @create_at 2024-03-14 21:10
 */
@Data
@ApiModel(description = "修改个人信息表单实体")
public class ChangeFormDTO {

    @NotNull(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @ApiModelProperty(value = "密码")
    private String password1;

    @ApiModelProperty(value = "密码")
    private String password2;

    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value="用户名",required = true)
    private String name;

    @NotNull(message = "头像url不能为空")
    @ApiModelProperty(value="头像",required = true)
    private String avatar;

    @ApiModelProperty(value="用户id",required = false)
    private Long id;
}
