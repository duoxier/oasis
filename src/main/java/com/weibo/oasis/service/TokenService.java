package com.weibo.oasis.service;

import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.dao.TokenDao;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.po.TokenPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    @Resource
    private TokenDao tokenDao;

    public Integer checkToken(String token){
        Integer userId = tokenDao.checkToken(token);
        if (userId == null){
            throw new NEException(ServiceError.TOKEN_EXPIRED);
        }
        return userId;
    }

    public RestResponse delete(Integer id){
        try {
            TokenPO po = tokenDao.findByUserId(id);
            if (po == null){
                throw new NEException(ServiceError.LOGOUT_ERROR);
            }
            tokenDao.delete(po.getToken());
            return RestResponse.success();
        }catch (Exception e){
            LOGGER.error("TokenService delete error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }
    }
}
