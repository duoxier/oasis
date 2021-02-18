package com.weibo.oasis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FollowConfig {

    @Value("${friendship.create}")
    private String create;

    @Value("${friendship.destroy}")
    private String destroy;

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getDestroy() {
        return destroy;
    }

    public void setDestroy(String destroy) {
        this.destroy = destroy;
    }
}
