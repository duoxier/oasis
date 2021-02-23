package com.weibo.oasis.controller.v1;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.FriendshipService;
import com.weibo.oasis.vo.OperateFriendShipCreateVO;
import com.weibo.oasis.vo.search.SearchTimelineUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "FriendshipController")
@RestController
@RequestMapping("/api/v1/*")
public class FriendshipController {

    @Resource
    private FriendshipService friendshipService;

    @ApiOperation("添加关注")
    @RequestMapping(value = "/friendship/follow/create", method = RequestMethod.GET)
    public RestResponse followCreate(OperateFriendShipCreateVO vo){
        if (vo.getUid().isEmpty()){
            return RestResponse.error(ServiceError.UID_NULL);
        }
        if (vo.getFollowNum() == null){
            return RestResponse.error(ServiceError.FOLLOW_NUM_ERROR);
        }
        if (vo.getFollowNum()<=0 || vo.getFollowNum() > 10){
            return RestResponse.error(ServiceError.FOLLOW_NUM_ERROR);
        }
        return friendshipService.followCreate(vo);
    }

    @ApiOperation("添加粉丝")
    @RequestMapping(value = "/friendship/following/create", method = RequestMethod.GET)
    public RestResponse followingCreate(OperateFriendShipCreateVO vo){
        if (vo.getUid().isEmpty()){
            return RestResponse.error(ServiceError.UID_NULL);
        }
        if (vo.getFollowNum() == null){
            return RestResponse.error(ServiceError.FOLLOW_NUM_ERROR);
        }
        if (vo.getFollowNum()<=0 || vo.getFollowNum() > 10){
            return RestResponse.error(ServiceError.FOLLOW_NUM_ERROR);
        }
        return friendshipService.followingCreate(vo);
    }
}
