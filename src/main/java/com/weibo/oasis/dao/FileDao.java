package com.weibo.oasis.dao;

import com.weibo.oasis.po.FilePO;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao {

    void create(FilePO po);
}
