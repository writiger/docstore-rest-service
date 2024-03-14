package com.ds.user.controller;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.C;
import com.ds.common.domain.R;
import com.ds.common.exception.CommonException;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.vo.UserVo;
import com.ds.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author writiger
 * @description 用户接口入口
 * @create_at 2024-03-11 12:33
 */
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    @ApiOperation("用户登录接口")
    @PostMapping("login")
    public R<UserVo> login(@RequestBody @Validated LoginFormDTO loginFormDTO){
        UserVo loginVo = new UserVo();
        try {
            loginVo = userService.login(loginFormDTO);
        }catch (CommonException e){
            // 返回自定义错误
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(loginVo);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("register")
    public R<Void> register(@RequestBody @Validated RegisterFormDTO registerFormDTO){
        try{
            userService.register(registerFormDTO);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok();
    }

    @ApiOperation("获取验证码接口")
    @GetMapping("/verify/{email}")
    @ResponseBody
    public R<Void> verify(@PathVariable("email") String email){
        try {
            userService.verify(email);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok();
    }

    @ApiOperation("获取个人信息")
    @GetMapping("")
    private R<UserVo> info(@RequestHeader("token")String token){
        UserVo userVo;
        try{
            userVo = userService.infoByToken(token);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(userVo);
    }

}
