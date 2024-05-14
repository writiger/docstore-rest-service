package com.ds.belong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.belong.domain.po.Belong;
import com.ds.belong.service.IBelongService;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ds.common.constants.Constants.AUTH_KEY;

/**
 * @author writiger
 * @description
 * @create_at 2024-04-20 15:03
 */
@Api(tags = "所属相关接口")
@RestController
@RequestMapping("/belong")
@RequiredArgsConstructor
public class BelongController {

    private final IBelongService belongService;

    @ApiOperation("查询所属列表")
    @GetMapping("/list")
    public R<List<Belong>> belongList(){
        List<Belong> belongList;
        try{
            belongList = belongService.lambdaQuery().list();
        }catch (CommonException e){
            // 返回自定义错误
            return R.error(e);
        }catch (Exception e){
            return R.error("Unknown Error");
        }
        return R.ok(belongList);
    }
}
