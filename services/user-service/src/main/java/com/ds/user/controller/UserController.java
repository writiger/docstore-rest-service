package com.ds.user.controller;

import com.ds.common.domain.R;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import com.ds.user.domain.dto.ChangeFormDTO;
import com.ds.user.domain.dto.ChangePasswdFormDTO;
import com.ds.user.domain.dto.LoginFormDTO;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.common.domain.vo.UserVo;
import com.ds.user.domain.vo.UserLoginVO;
import com.ds.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ds.common.constants.Constants.AUTH_KEY;

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
    public R<UserLoginVO> login(@RequestBody @Validated LoginFormDTO loginFormDTO){
        UserLoginVO userLoginVO;
        try {
            userLoginVO = userService.getPasswd(loginFormDTO);
        }catch (CommonException e){
            // 返回自定义错误
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(userLoginVO);
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
    private R<UserVo> info(@RequestHeader(AUTH_KEY)String userId){
        UserVo userVo;
        try{
            userVo = userService.infoByToken(userId);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(userVo);
    }

    @ApiOperation("注销")
    @DeleteMapping("")
    private R<Void> remove(@RequestHeader(AUTH_KEY)String userId){
        try{
            userService.removeByToken(userId);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok();
    }

    @ApiOperation("修改个人信息")
    @PutMapping("")
    private R<UserVo> change(@RequestHeader(AUTH_KEY)String userId,
                             @RequestBody @Validated ChangeFormDTO changeFormDTO){
        UserVo userVo;
        try{
            userVo = userService.changeInfoByToken(changeFormDTO,userId);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(userVo);
    }

    @ApiOperation("申请修改密码")
    @PostMapping("/passwd/{email}")
    private R<Void> lostPasswd(@PathVariable("email") String email){
        try{
            userService.verifyPasswd(email);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok();
    }

    @ApiOperation("通过验证码修改密码")
    @PutMapping("/passwd/{verifyCode}")
    private R<Void> changePasswd(@RequestBody @Validated ChangePasswdFormDTO changePasswdFormDTO,
                                 @PathVariable("verifyCode") String verifyCode){
        try{
            userService.changePasswd(changePasswdFormDTO,verifyCode);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok();
    }

    @ApiOperation("验证用户等级")
    @GetMapping("/level/{userId}")
    private R<UserLevel>  getLevel(@PathVariable("userId") Long userId){
        UserLevel level;
        try{
            level = userService.getLevel(userId);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(level);
    }

    @ApiOperation("修改昵称")
    @PutMapping("/name/{name}")
    private R<Void> changeName(@PathVariable("name") String name,@RequestHeader(AUTH_KEY)String userId){
        try{
            userService.changeName(name,userId);
        }catch (CommonException e){
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok();
    }
}
