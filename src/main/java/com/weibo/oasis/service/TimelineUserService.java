package com.weibo.oasis.service;

import com.alibaba.fastjson.JSONObject;
import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.config.FollowConfig;
import com.weibo.oasis.config.TimelineConfig;
import com.weibo.oasis.config.VersionConfig;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.po.TimelineUserPO;
import com.weibo.oasis.vo.search.SearchTimelineUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class TimelineUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimelineUserService.class);

    @Resource
    private FollowConfig followConfig;

    @Resource
    private TimelineConfig timelineConfig;

    @Resource
    private VersionConfig versionConfig;

    public RestResponse getTimelineUser(SearchTimelineUserVO vo){

        String url = timelineConfig.getUser();
        RestTemplate restTemplate = new RestTemplate();
        LOGGER.info("get timeline user url: {}", url);
        TimelineUserPO po = new TimelineUserPO();
        try{
            ResponseEntity<String> result = restTemplate.getForEntity(url+"?debug=true&cuid="+vo.getCuid()+"&ouid="+vo.getOuid()+"&version="+versionConfig.getVersion()+"&cursor=-1&count=10&feature=0", String.class);
            String body = result.getBody();
            HttpStatus statusCode =result.getStatusCode();
            JSONObject jsonObject = null;
            Integer code = -1;
            String msg = null;
            if (body != null){
                jsonObject=JSONObject.parseObject(body);
            }
            if (statusCode == HttpStatus.OK){
                if (jsonObject != null){
                    code=jsonObject.getInteger("code");
                    msg = jsonObject.getString("msg");
                    if (code == 0 && msg.equals("success")){
                        LOGGER.info("timeline user success");
                        JSONObject userJsonObject = jsonObject.getJSONObject("data").getJSONObject("user");
                        po.setName(userJsonObject.getString("name"));
                        po.setAvatar(userJsonObject.getString("background"));
                        po.setFollowerCount(userJsonObject.getInteger("follower_count"));
                        po.setFollowingCount(userJsonObject.getInteger("following_count"));
                        return RestResponse.success(po);
                    }
                }
            }
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }catch (Exception e){
            LOGGER.error("Timeline user error {}", e);
            throw new NEException(ServiceError.OASIS_SERVICE_ERROR);
        }
    }

}
