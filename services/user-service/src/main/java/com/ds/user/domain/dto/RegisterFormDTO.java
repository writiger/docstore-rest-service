package com.ds.user.domain.dto;

import com.ds.common.utils.ShortUUID;
import com.ds.user.domain.po.User;
import com.ds.user.enums.UserLevel;
import com.ds.user.enums.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

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

    @NotNull(message = "所属单位不能为空")
    @ApiModelProperty(value = "所属单位", required = true)
    private String belong;

    @NotNull(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String verify;

    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value="用户名",required = true)
    private String name;

    public static User converseTOUser(RegisterFormDTO userDto){
        User user = new User();
        user.setAccount(userDto.getAccount());
        user.setPassword(userDto.getPassword1());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLevel(UserLevel.NORMAL);
        user.setBelong(userDto.getBelong());
        user.setStatus(UserStatus.FROZEN);
        user.setUuid(ShortUUID.generateShortUUID());
        user.setAvatar("");
        return user;
    }
}
