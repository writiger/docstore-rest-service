package com.ds.gateway.factory;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-18 13:37
 */
@Component
public class CustomLoadBalancerGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomLoadBalancerGatewayFilterFactory.Config> {

    private final DiscoveryClient discoveryClient;

    public CustomLoadBalancerGatewayFilterFactory(DiscoveryClient discoveryClient) {
        super(Config.class);
        this.discoveryClient = discoveryClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String belong = request.getHeaders().getFirst("Belong");
            if(belong != null){
                List<ServiceInstance> instances = discoveryClient.getInstances("doc-service");
                Optional<ServiceInstance> instance = instances.stream()
                        .filter(serviceInstance -> belong.equalsIgnoreCase(serviceInstance.getMetadata().get("belong"))).findFirst();
                if (instance.isPresent()) {
                    URI newUri = URI.create("http://" + instance.get().getHost() + ":" + instance.get().getPort() + exchange.getRequest().getPath().toString());
                    ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri)
                            .method(request.getMethod())
                            .headers(httpHeaders -> request.getHeaders())
                            .build();
                    ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
                    Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
                    Route newRoute = Route.async().asyncPredicate(route.getPredicate()).filters(route.getFilters()).id(route.getId())
                            .order(route.getOrder()).uri(newUri).build();
                    exchange.getAttributes().put(GATEWAY_ROUTE_ATTR,newRoute);
                    return chain.filter(exchange.mutate().request(newRequest).build());
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Configuration properties if needed
    }
}