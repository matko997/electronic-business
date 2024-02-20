package com.electronicbusiness.bidmaster.config;

import com.pusher.rest.Pusher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig {
  private final PusherProperties pusherProperties;

  @Bean
  public Pusher pusher() {
    Pusher pusher =
        new Pusher(pusherProperties.appId(), pusherProperties.key(), pusherProperties.secret());
    pusher.setCluster(pusherProperties.cluster());
    pusher.setEncrypted(pusherProperties.encrypted());
    return pusher;
  }
}
