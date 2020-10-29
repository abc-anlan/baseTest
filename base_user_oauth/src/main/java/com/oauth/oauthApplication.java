package com.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//未修
@SpringBootApplication
@EnableAuthorizationServer
public class oauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(oauthApplication.class,args);
    }
}
