package com.demo.geteway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
@Component
public class ipFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress ip = request.getRemoteAddress();
        RequestPath path = request.getPath();
        System.out.println("ip---"+ip.toString()+"======"+"path---"+path.toString());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
