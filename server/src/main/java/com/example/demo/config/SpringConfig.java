package com.example.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling // 기본 스케줄러 관련 설정 on
@EnableCaching // 기본 캐시 관련 설정 on
@EnableAsync // 기본 비동기 관련 설정 on
public class SpringConfig {

    // API 통신 등을 위한 RestTemplate Bean 설정
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
