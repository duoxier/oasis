package com.weibo.oasis.controller.v1;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.service.WaterService;
import com.weibo.oasis.vo.WaterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "WaterController")
@RestController
@RequestMapping("/api/v1/*")
public class WaterController {

    @Resource
    private WaterService waterService;

    @ApiOperation("水滴充值")
    @RequestMapping(value = "/water/charges", method = RequestMethod.GET)
    public RestResponse charge(WaterVO vo){
        return waterService.charge(vo);
    }
}
