package com.demo.geteway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class AnthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       //1.获取请求对象
        //2.获取响应对象
        //3判断当前请求是否为登录请求，是--放行
        //4.获取当前请求头信息
        //5.判断当前请求头信息中是否存在token，是--解析
        //6.如果合法，放行
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
