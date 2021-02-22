package com.weibo.oasis.controller.v1;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.TimelineUserService;
import com.weibo.oasis.vo.WeiboPositionVO;
import com.weibo.oasis.vo.search.SearchTimelineUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "TimelineController")
@RestController
@RequestMapping("/api/v1/*")
public class TimelineController {

    @Resource
    private TimelineUserService timelineUserService;

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/timeline/user", method = RequestMethod.GET)
    public RestResponse positions(SearchTimelineUserVO vo){
        if (vo.getCuid().isEmpty() || vo.getOuid().isEmpty()){
            return RestResponse.error(ServiceError.UID_NULL);
        }
        return timelineUserService.getTimelineUser(vo);
    }
}
