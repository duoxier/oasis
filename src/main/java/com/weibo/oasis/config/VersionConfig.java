package com.weibo.oasis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersionConfig {

    @Value("${version}")
    private String version;
}
