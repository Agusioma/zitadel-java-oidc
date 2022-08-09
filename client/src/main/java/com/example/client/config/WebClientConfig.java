package com.example.client.config;

import com.example.client.support.AccessTokenInterceptor;
import com.example.client.support.TokenAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
class WebClientConfig {

    private final TokenAccessor tokenAccessor;

    @Bean
    @Qualifier("zitadel")
    RestTemplate restTemplate() {
        return new RestTemplateBuilder() //
                .interceptors(new AccessTokenInterceptor(tokenAccessor)) //
                .build();
    }
}
