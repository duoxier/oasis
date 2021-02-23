package com.weibo.oasis.service;

import com.alibaba.fastjson.JSONObject;
import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.config.FriendshipConfig;
import com.weibo.oasis.config.VersionConfig;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.vo.OperateFriendShipCreateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class FriendshipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendshipService.class);

    @Resource
    private FriendshipConfig friendshipConfig;

    @Resource
    private VersionConfig versionConfig;

    //添加关注
    public RestResponse followCreate(OperateFriendShipCreateVO vo) {
        String[] uids = new String[]{
                "5058699452", "5057337104", "5057337105", "5057337106", "5057337107", "5057337108", "5057337109", "5057337110", "5057337112", "5057337113"};
        int successCount = 0;
        int failCount = 0;
        for (int i = 0; i < vo.getFollowNum(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            String url = friendshipConfig.getCreate()+"?debug=true&version="+versionConfig.getVersion()+"&create_page_name=default&cuid="+vo.getUid()+"&ouids="+uids[i];
            LOGGER.info("friendship create url: {}", url);
            Integer code = -1;
            String msg = null;
            try {
                ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
                String body = result.getBody();
                HttpStatus statusCode = result.getStatusCode();
                JSONObject jsonObject = JSONObject.parseObject(body);
                if (statusCode == HttpStatus.OK) {
                    if (jsonObject != null){
                        LOGGER.info("friendship create response is : {}",jsonObject.toJSONString());
                        code = jsonObject.getInteger("code");
                        msg = jsonObject.getString("msg");
                        if (code == 0 && msg.equals("success")){
                            successCount+=1;
                        }else {
                            failCount+=1;
                        }
                    }
                }else {
                    return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
                }
            } catch (Exception e) {
                LOGGER.error("friendship create error {}", e);
                throw new NEException(ServiceError.OASIS_SERVICE_ERROR);
            }
            }
        Map<String, Integer> map = new HashMap<>();
        if (successCount == vo.getFollowNum()){
            return RestResponse.success();
        }else {
            map.put("successCount", successCount);
            map.put("failCount", failCount);
            return RestResponse.success(map);
        }
    }

    //添加粉丝
    public RestResponse followingCreate(OperateFriendShipCreateVO vo){
        String[] uids = new String[]{
                "5058699452", "5057337104", "5057337105", "5057337106", "5057337107", "5057337108", "5057337109", "5057337110", "5057337112", "5057337113"};
        int successCount = 0;
        int failCount = 0;
        for (int i = 0; i < vo.getFollowNum(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            String url = friendshipConfig.getCreate()+"?debug=true&version="+versionConfig.getVersion()+"&create_page_name=default&cuid="+uids[i]+"&ouids="+vo.getUid();
            LOGGER.info("friendship create url: {}", url);
            Integer code = -1;
            String msg = null;
            try {
                ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
                String body = result.getBody();
                HttpStatus statusCode = result.getStatusCode();
                JSONObject jsonObject = JSONObject.parseObject(body);
                if (statusCode == HttpStatus.OK) {
                    if (jsonObject != null){
                        LOGGER.info("friendship create response is : {}",jsonObject.toJSONString());
                        code = jsonObject.getInteger("code");
                        msg = jsonObject.getString("msg");
                        if (code == 0 && msg.equals("success")){
                            successCount+=1;
                        }else {
                            failCount+=1;
                        }
                    }
                }else {
                    return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
                }
            } catch (Exception e) {
                LOGGER.error("friendship create error {}", e);
                throw new NEException(ServiceError.OASIS_SERVICE_ERROR);
            }
        }
        Map<String, Integer> map = new HashMap<>();
        if (successCount == vo.getFollowNum()){
            return RestResponse.success();
        }else {
            map.put("successCount", successCount);
            map.put("failCount", failCount);
            return RestResponse.success(map);
        }
    }
}