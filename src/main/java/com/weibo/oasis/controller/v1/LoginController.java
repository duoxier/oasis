package com.weibo.oasis.controller.v1;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.LoginService;
import com.weibo.oasis.service.TokenService;
import com.weibo.oasis.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "LoginController")
@RestController
@RequestMapping("/api/v1/*")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private TokenService tokenService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResponse create(@RequestBody LoginVO vo){
        if (vo.getPhone().isEmpty()){
            return RestResponse.error(ServiceError.PHONE_NULL);
        }
        if (vo.getPassword().isEmpty()){
            return RestResponse.error(ServiceError.PASSWORD_NULL);
        }
        return loginService.create(vo);

    }

    @ApiOperation("用户登出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RestResponse create(Integer id){
        if (id == null){
            return RestResponse.error(ServiceError.UID_NULL);
        }
        return tokenService.delete(id);
    }
}
