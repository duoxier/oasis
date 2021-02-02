package com.weibo.oasis.service;

import com.alibaba.fastjson.JSONObject;
import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.config.NearbyConfig;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.vo.WeiboPositionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import javax.annotation.Resource;

@Service
public class NearbyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearbyService.class);

    @Resource
    private NearbyConfig nearbyConfig;

    public void weiboPosition(WeiboPositionVO vo){
        RestTemplate restTemplate = new RestTemplate();
        try {
            System.out.println("responseEntity : ");
        }catch (Exception e ){
            LOGGER.error("NearbyService weiboPosition error {}", e);
            throw new NEException(ServiceError.OASIS_SERVICE_ERROR);
        }
    }

    public RestResponse clearViewer(String uid){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("nearbyConfig clear-viewer"+nearbyConfig.getClear_viewer());
        String url = nearbyConfig.getClear_viewer()+"?uid="+uid;
        Integer code = -1;
        String msg = null;
        try {
            ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
            String body = result.getBody();
            HttpStatus statusCode =result.getStatusCode();
            JSONObject jsonObject=JSONObject.parseObject(body);
            if (statusCode == HttpStatus.OK){
                code = Integer.parseInt(jsonObject.get("code").toString());
                msg = jsonObject.get("msg").toString();
                if (code == 0 && msg.equals("succ")){
                    return RestResponse.success();
                }
            }
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }catch (Exception e){
            LOGGER.error("NearbyService clearViewer error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }
    }
}
