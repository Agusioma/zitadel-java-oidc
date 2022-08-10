package com.example.resourceserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration applied on all web endpoints defined for this
 * application. Any configuration on specific resources is applied
 * in addition to these global rules.
 */
@Configuration
@RequiredArgsConstructor
class ApiSecurityConfig {

    /**
     * Configures basic security handler per HTTP session.
     * <p>
     * <ul>
     * <li>Stateless session (no session kept server-side)</li>
     * <li>CORS set up</li>
     * <li>Require the role "ACCESS" for all api paths</li>
     * <li>JWT converted into Spring token</li>
     * </ul>
     *
     * @param httpSecurity security configuration
     * @throws Exception any error
     */
    @Bean
    public SecurityFilterChain securityChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();

        httpSecurity.sessionManagement(it -> {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        httpSecurity.authorizeRequests(it -> {
            // declarative route configuration
            // .mvcMatchers("/api").hasAuthority("ROLE_ACCESS")
            it.mvcMatchers("/api/**").authenticated();
            // add additional routes
            it.anyRequest().authenticated(); //
        });
        httpSecurity.oauth2ResourceServer().opaqueToken();

        return httpSecurity.build();
    }

}