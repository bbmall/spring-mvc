package com.bbmall.springmvc.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bmalinowski on 10.05.18.
 */
@Configuration
@EnableCaching
@EnableScheduling
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @CacheEvict(allEntries = true, cacheNames = {"OPEN_WEATHER_MAP_CACHE"})
    @Scheduled(fixedDelayString = "${cache.fixed.delay}")
    public void cacheEvict() {
    }

}
