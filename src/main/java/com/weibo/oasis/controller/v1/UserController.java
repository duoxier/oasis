package com.weibo.oasis.controller.v1;

import com.ne.boot.common.entity.Page;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.UserService;
import com.weibo.oasis.vo.OperateUserVO;
import com.weibo.oasis.vo.list.UserListVO;
import com.weibo.oasis.vo.search.SearchUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "UserController")
@RestController
@RequestMapping("/api/v1/*")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户新增")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public RestResponse create(@RequestBody OperateUserVO vo){
        if (StringUtils.isEmpty(vo.getUsername())){
            return RestResponse.error(ServiceError.USERNAME_NULL);
        }
        if (StringUtils.isEmpty(vo.getPassword())){
            return RestResponse.error(ServiceError.PASSWORD_NULL);
        }
        if (vo.getStatus() == null){
            return RestResponse.error(ServiceError.STATUS_NULL);
        }
        return userService.create(vo);
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public RestResponse update(@PathVariable("id") Integer id, @RequestBody OperateUserVO vo){
        return userService.update(vo, id);
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public RestResponse update(@PathVariable("id") Integer id){
        return userService.delete(id);
    }

    @ApiOperation("查询用户")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public RestResponse<UserListVO> all(@ApiIgnore SearchUserVO vo, Page page){
        return userService.all(vo, page);
    }
}
