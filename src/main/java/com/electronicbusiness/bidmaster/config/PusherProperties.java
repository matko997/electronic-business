package com.electronicbusiness.bidmaster.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pusher")
public record PusherProperties(
    String appId, String key, String secret, String cluster, boolean encrypted) {}
