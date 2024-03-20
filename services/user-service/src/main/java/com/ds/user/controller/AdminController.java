package com.ds.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import com.ds.user.domain.po.User;
import com.ds.user.domain.vo.UserLoginVO;
import com.ds.user.service.IAdminService;
import com.ds.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 16:21
 */
@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final IAdminService adminService;

    @ApiOperation("查询用户列表")
    @GetMapping("/user")
    public R<PageDTO<User>> userList(PageQuery pageQuery){
        Page<User> userList;
        try{
            userList = adminService.lambdaQuery().ne(User::getLevel, UserLevel.ROOT).page(pageQuery.toMpPage());
        }catch (CommonException e){
            // 返回自定义错误
            return R.error(e);
        }catch (Exception e){
            // 记录未知错误
            logger.error(e.getMessage());
            return R.error("Unknown Error");
        }
        return R.ok(PageDTO.of(userList));
    }
}
