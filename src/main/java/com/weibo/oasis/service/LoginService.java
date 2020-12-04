package com.weibo.oasis.service;

import com.ne.boot.common.util.CommonUtil;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.dao.LoginDao;
import com.weibo.oasis.dao.TokenDao;
import com.weibo.oasis.dao.UserDao;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.po.TokenPO;
import com.weibo.oasis.po.UserPO;
import com.weibo.oasis.utils.DateUtil;
import com.weibo.oasis.vo.LoginVO;
import com.weibo.oasis.vo.UserLoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Resource
    private UserDao userDao;

    @Resource
    private TokenDao tokenDao;

    @Resource
    private LoginDao loginDao;

    public RestResponse create(LoginVO vo){
        try {
            UserPO po =loginDao.findByUsernameAndPassword(vo);
            if (po == null){
                return RestResponse.error(ServiceError.PHONE_OR_PASSWORD_INCORRECT);
            }
            if (po.getStatus() == -1){
                return RestResponse.error(ServiceError.USER_STATUS_ERROR);
            }
            //生成token
            String token = buildToken(po.getId());
            UserLoginVO userVO = buildUserVO(new UserLoginVO(), po);
            userVO.setToken(token);
            return RestResponse.success(userVO);
        }catch (Exception e){
            LOGGER.error("LoginService create error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }

    }

    private String buildToken(Integer userId) {
        TokenPO po = tokenDao.findByUserId(userId);
        if (po == null){
            po = new TokenPO();
            buildTokenPO(userId, po);
            tokenDao.create(po);
        }else {
            buildTokenPO(userId, po);
            tokenDao.update(po);
        }
        return po.getToken();
    }

    private void buildTokenPO(Integer userId,TokenPO po) {
        po.setUserId(userId);
        po.setToken(CommonUtil.getUUID());
        po.setExpireTime(DateUtil.getTokenExpireTime());
    }

    private UserLoginVO buildUserVO(UserLoginVO vo, UserPO po){
        vo.setId(po.getId());
        vo.setPhone(po.getPhone());
        vo.setUsername(po.getUsername());
        vo.setEmail(po.getEmail());
        return vo;
    }


}
