package com.weibo.oasis.service;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.config.LikeConfig;
import com.weibo.oasis.vo.LikeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LikeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LikeService.class);

    @Resource
    private LikeConfig likeConfig;

    //1:1 cuid like uid
    public void create(LikeVO vo){

    }
}
