package com.weibo.oasis.dao;

import com.weibo.oasis.po.TokenPO;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDao {

    void create(TokenPO po);
    void update(TokenPO po);
    void delete(String token);
    TokenPO findByUserId(Integer id);
    Integer checkToken(String token);
}
