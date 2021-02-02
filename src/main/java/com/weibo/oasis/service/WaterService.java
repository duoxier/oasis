package com.weibo.oasis.service;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.vo.WaterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
public class WaterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaterService.class);

    public RestResponse charge(WaterVO vo){
        //http://172.16.181.40:8868/water/add?uid=6440235488&value=10
        String url = "http://172.16.181.40:8868/water/add";
        RestTemplate restTemplate = new RestTemplate();
        Integer code = -1;
        String msg = null;
//        Map<String, Object> map = null;
//        map.put("uid", vo.getCuid());
//        map.put("value", vo.getValue());

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("uid", vo.getCuid());
        params.add("value", vo.getValue());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        try {
            ResponseEntity<String> results=restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
            System.out.println("results: "+results);
            return RestResponse.success();
        }catch (Exception e){
            LOGGER.error("WaterService charge error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }
    }
}
