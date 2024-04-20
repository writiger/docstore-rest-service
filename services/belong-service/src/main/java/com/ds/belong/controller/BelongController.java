package com.ds.belong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.belong.domain.po.Belong;
import com.ds.belong.service.IBelongService;
import com.ds.common.domain.R;
import com.ds.common.domain.dto.PageDTO;
import com.ds.common.domain.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R<PageDTO<Belong>> belongList(@RequestHeader(AUTH_KEY)Long userId,PageQuery pageQuery){
        Page<Belong> belongList;
        belongList = belongService.belongList(pageQuery,userId);
        return R.ok(PageDTO.of(belongList));
    }
}
