package com.weibo.oasis.controller.v1;


import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.LikeService;
import com.weibo.oasis.vo.LikeVO;
import com.weibo.oasis.vo.NearbyUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "LikeController")
@RestController
@RequestMapping("/api/v1/*")
public class LikeController {

    @Resource
    private LikeService likeService;

    @ApiOperation("点赞")
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public RestResponse create(@RequestBody LikeVO vo){
        if (vo.getSid().isEmpty()){
            return RestResponse.error(ServiceError.SID_NULL);
        }
        if (vo.getUids().length == 0){
            return RestResponse.error(ServiceError.UID_NULL);
        }
        return RestResponse.success();
    }
}
