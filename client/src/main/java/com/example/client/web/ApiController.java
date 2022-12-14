package com.example.client.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ApiController {

    @Qualifier("zitadel")
    private final RestTemplate restTemplate;

    @GetMapping("/pings/me")
    Object pingServer(Authentication auth) {
        var response = restTemplate.getForObject("http://localhost:18090/api/ping/me", Map.class);
        return response;
    }
}
