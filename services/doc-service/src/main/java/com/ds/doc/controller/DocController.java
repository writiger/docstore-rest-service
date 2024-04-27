package com.ds.doc.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author writiger
 * @description 文献接口
 * @create_at 2024-04-27 13:29
 */
@Api(tags = "文献相关接口")
@RestController
@RequestMapping("/doc")
@RequiredArgsConstructor
public class DocController {
}
