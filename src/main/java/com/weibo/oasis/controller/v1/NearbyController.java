package com.weibo.oasis.controller.v1;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.NearbyService;
import com.weibo.oasis.vo.NearbyUserVO;
import com.weibo.oasis.vo.WeiboPositionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "NearbyController")
@RestController
@RequestMapping("/api/v1/*")
public class NearbyController {

    @Resource
    private NearbyService nearbyService;

    @ApiOperation("微博常用位置")
    @RequestMapping(value = "/nearby/positions", method = RequestMethod.GET)
    public RestResponse positions(WeiboPositionVO vo){
        nearbyService.weiboPosition(vo);
        return RestResponse.success();
    }
    @ApiOperation("清除位置缓存")
    @RequestMapping(value = "/nearby/clear_viewer", method = RequestMethod.GET)
    public RestResponse clear(@ApiIgnore NearbyUserVO vo){
        if (vo.getUid() == null){
            return RestResponse.error(ServiceError.UID_NULL);
        }
        return nearbyService.clearViewer(vo.getUid());
    }

}
