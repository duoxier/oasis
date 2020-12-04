package com.weibo.oasis.dao;

import com.ne.boot.common.entity.Page;
import com.weibo.oasis.po.UserPO;
import com.weibo.oasis.vo.search.SearchUserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void create(UserPO po);
    void update(UserPO po);
    void delete(Integer id);
    UserPO findById(Integer id);
    UserPO findByUsername(String username);
    UserPO findByPhone(String phone);
    UserPO findByEmail(String email);
    List<UserPO> all(SearchUserVO vo, Page page);

}
