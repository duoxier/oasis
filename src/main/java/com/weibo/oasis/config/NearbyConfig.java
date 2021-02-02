package com.weibo.oasis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class NearbyConfig {

    @Value("${nearby.clear-viewer}")
    private String clear_viewer;

    public String getClear_viewer() {
        return clear_viewer;
    }

    public void setClear_viewer(String clear_viewer) {
        this.clear_viewer = clear_viewer;
    }
}
