package com.weibo.oasis.service;

import com.weibo.oasis.config.FollowConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TimelineUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimelineUserService.class);

    @Resource
    private FollowConfig followConfig;

}
