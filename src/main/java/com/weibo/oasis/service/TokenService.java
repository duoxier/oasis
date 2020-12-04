package com.weibo.oasis.service;

import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.dao.TokenDao;
import com.weibo.oasis.error.ServiceError;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TokenService {

    @Resource
    private TokenDao tokenDao;

    public Integer checkToken(String token){
        Integer userId = tokenDao.checkToken(token);
        if (userId == null){
            throw new NEException(ServiceError.TOKEN_EXPIRED);
        }
        return userId;
    }
}
