package com.weibo.oasis.service;

import com.alibaba.fastjson.JSONObject;
import com.ne.boot.common.exception.NEException;
import com.weibo.oasis.RestResponse;
import com.weibo.oasis.config.NearbyConfig;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.po.WeiboPositionPO;
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

    public RestResponse weiboPosition(WeiboPositionVO vo){
        WeiboPositionPO po = new WeiboPositionPO();
        RestTemplate restTemplate = new RestTemplate();
        String url = nearbyConfig.getPosition();
        LOGGER.info("get weibo position url: {}", url);
        try {
            ResponseEntity<String> result = restTemplate.getForEntity(url+"?access_key="+vo.getAccess_key()+"&uid="+vo.getUid()+"&deep="+vo.getDeep()+"&type="+vo.getType(), String.class);
            String body = result.getBody();
            HttpStatus statusCode =result.getStatusCode();
            JSONObject jsonObject = null;
            if (body != null){
                jsonObject=JSONObject.parseObject(body);
            }
            if (statusCode == HttpStatus.OK){
                JSONObject current = null;
                if (jsonObject !=null){
                     Object object=jsonObject.getObject("current", Object.class);
                     if (object.toString().equals( "[]")){
                         return RestResponse.success();
                     }
                    current= jsonObject.getJSONObject("current");
                    po.setTime(current.getString("time"));
                    po.setCity(current.getJSONObject("city").getString("name"));
                    po.setCountry(current.getJSONObject("country").getString("name"));
                    po.setProvince(current.getJSONObject("province").getString("name"));
                    po.setIsIp(current.getString("isip"));
                    return RestResponse.success(po);
                }
            }
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
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
