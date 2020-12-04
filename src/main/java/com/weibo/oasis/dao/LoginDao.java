package com.weibo.oasis.dao;

import com.weibo.oasis.po.UserPO;
import com.weibo.oasis.vo.LoginVO;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {

    UserPO findByUsernameAndPassword(LoginVO vo);

}
