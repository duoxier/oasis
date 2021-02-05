package com.weibo.oasis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LikeConfig {

    @Value("${like.create}")
    private String create;

    @Value("${like.destroy}")
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
