package com.example.client.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAccessor {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuth2AccessToken retrieveAccessToken() {
        return getAccessToken(SecurityContextHolder.getContext().getAuthentication());
    }

    public OAuth2AccessToken getAccessToken(Authentication clientAuth) {

        log.debug("Get AccessToken for current user {}: begin", clientAuth.getName());
        var authToken = (OAuth2AuthenticationToken) clientAuth;
        var clientId = authToken.getAuthorizedClientRegistrationId();
        var username = clientAuth.getName();
        var authorizedClient = authorizedClientService.loadAuthorizedClient(clientId, username);

        if (authorizedClient == null) {
            log.warn("Get AccessToken for current user failed: client not found");
            return null;
        }else{
            var clientAccessToken = authorizedClient.getAccessToken();
            log.debug("Get AccessToken for current user {}: end", clientAuth.getName());
        }

        return accessToken;

    }
}
