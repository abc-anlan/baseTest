package com.demo.geteway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableEurekaClient
public class getewayApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(getewayApplicaton.class,args);
    }


//    @Bean  //限流操作  redis  令牌桶算法
//    public KeyResolver ipKeyResolver(){
//        return new KeyResolver(){
//
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
//
//            }
//        };
//    }
}
