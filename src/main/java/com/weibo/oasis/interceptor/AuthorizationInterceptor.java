package com.weibo.oasis.interceptor;

import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.service.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Resource
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");
        //获取请求url
        String url = request.getRequestURI();
        System.out.println("request url: "+url);
        if (url.equals("/error")){
            throw new NEException(ServiceError.NOT_FOUND);
        }
        if (token == null){
            throw new NEException(ServiceError.TOKEN_ERROR);
        }
        if (tokenService.checkToken(token) == null){
            throw new NEException(ServiceError.TOKEN_EXPIRED);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
