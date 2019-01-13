package com.academy.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Configuration
public class JwtConfig {
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;
    @Bean
    @Qualifier("tokenStore")
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer(){
        JwtAccessTokenConverter converter =new JwtAccessTokenConverter();
        Resource resource =new ClassPathResource("public.cert");
        String publicKey = null;
        try {
            publicKey =new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }
}