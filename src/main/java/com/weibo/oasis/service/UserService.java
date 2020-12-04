package com.weibo.oasis.service;

import com.ne.boot.common.entity.Page;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.dao.UserDao;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.po.UserPO;
import com.weibo.oasis.vo.OperateUserVO;
import com.weibo.oasis.vo.list.UserListVO;
import com.weibo.oasis.vo.search.SearchUserVO;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDao userDao;

    //添加用户
    public RestResponse create(OperateUserVO vo){
        try {
            UserPO po = buildUser(new UserPO(), vo);
            if (userDao.findByPhone(vo.getPhone()) != null){
                return RestResponse.error(ServiceError.PHONE_EXIST);
            }
            if (userDao.findByUsername(vo.getUsername()) != null){
                return RestResponse.error(ServiceError.USERNAME_EXIST);
            }
            if (userDao.findByEmail(vo.getEmail()) != null ){
                return RestResponse.error(ServiceError.EMAIL_EXIST);
            }
            po.setPhone(vo.getPhone());
            po.setEmail(vo.getEmail());
            po.setUsername(vo.getUsername());
            userDao.create(po);
            return RestResponse.success();
        }catch (Exception e){
            LOGGER.error("UserService create error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }
    }

    //更新用户
    public RestResponse update(OperateUserVO vo, Integer id){
        try {
            UserPO po = userDao.findById(id);
            if (po == null){
                return RestResponse.error(ServiceError.USER_NOT_EXIST);
            }
           //判断手机号是否已被使用
            if (vo.getPhone() != null){
                UserPO poPhone = userDao.findByPhone(vo.getPhone());
                if (poPhone != null && (!poPhone.getId().equals(id))){
                    return RestResponse.error(ServiceError.PHONE_EXIST);
                }
            }
            //判断邮箱是否已被使用
            if (vo.getEmail() != null){
                UserPO poEmail = userDao.findByEmail(vo.getEmail());
                if (poEmail != null && (!poEmail.getId().equals(id))){
                    return RestResponse.error(ServiceError.EMAIL_EXIST);
                }
            }
            //判断用户名是否已被使用
            if (vo.getUsername() != null){
                UserPO poUsername = userDao.findByUsername(vo.getUsername());
                if (poUsername != null && (!poUsername.getId().equals(id))){
                    return RestResponse.error(ServiceError.USERNAME_EXIST);
                }
            }
            po.setUsername(vo.getUsername());
            po.setPhone(vo.getPhone());
            if (po.getPassword() != null){
                po.setPassword(vo.getPassword());
            }
            po.setEmail(vo.getEmail());
            if (po.getStatus() != null){
                po.setStatus(vo.getStatus());
            }
            userDao.update(po);
            return RestResponse.success();
        }catch (Exception e){
            LOGGER.error("UserService update error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }
    }

    //删除用户
    public RestResponse delete(Integer id){
        try {
            UserPO po = userDao.findById(id);
            if (po == null){
                return RestResponse.error(ServiceError.USER_NOT_EXIST);
            }
            userDao.delete(id);
            return RestResponse.success();
        }catch (Exception e){
            LOGGER.error("UserService delete error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }

    }

    //查询用户列表
    public RestResponse<UserListVO> all(SearchUserVO vo, Page page){
        try {
            return RestResponse.success(buildUserList(userDao.all(vo, page)),page);
        }catch (Exception e){
            LOGGER.error("UserService all error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }

    }


    private UserPO buildUser(UserPO po, OperateUserVO vo){
        po.setPassword(vo.getPassword());
        po.setStatus(vo.getStatus());
        return po;

    }

    private List<UserListVO> buildUserList(List<UserPO> pos){
        List<UserListVO> vos = new ArrayList<>();
        for (UserPO po : pos){
            UserListVO vo = new UserListVO();
            vo.setId(po.getId());
            vo.setUsername(po.getUsername());
            vo.setPassword(po.getPassword());
            vo.setEmail(po.getEmail());
            vo.setStatus(po.getStatus());
            vo.setPhone(po.getPhone());
            vo.setCreateTime(po.getCreateTime());
            vo.setUpdateTime(po.getUpdateTime());
            vos.add(vo);
        }
        return vos;
    }


}
