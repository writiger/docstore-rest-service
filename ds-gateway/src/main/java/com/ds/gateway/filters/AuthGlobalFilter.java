package com.ds.gateway.filters;

import com.ds.common.exception.UnauthorizedException;
import com.ds.gateway.config.AuthProperties;
import com.ds.gateway.config.JwtProperties;
import com.ds.gateway.utils.JwtTool;
import com.ds.gateway.utils.ResponseDecorator;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ds.common.constants.Constants.AUTH_KEY;

/**
 * @author writiger
 * @description 全局登录校验过滤器
 * @create_at 2024-03-17 11:32
 */
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final PasswordEncoder passwordEncoder;

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //返回体装饰器
        ResponseDecorator decorator = new ResponseDecorator(exchange.getResponse(),
                jwtTool,
                jwtProperties.getTokenTTL(),
                passwordEncoder);
        // 1.获取request
        ServerHttpRequest request = exchange.getRequest();
        // 2.判断是否不需要登录拦截
        if(isExclude(request.getPath().toString())){
            if(isLogin(request.getPath().toString())){
                return chain.filter(exchange.mutate()
                        .response(decorator)
                        .build());
            }
            // 放行
            return chain.filter(exchange);
        }
        // 3.获取token
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");
        if(headers!=null && !headers.isEmpty()){
            token = headers.get(0);
        }
        // 4.校验并解析token
        Long userId =null;
        try{
            userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException e){
            // 拦截，设置相应状态码为401
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange serverWebExchange = exchange.mutate()
                .request(builder -> builder.header(AUTH_KEY, userInfo))
                .build();
        // 6.放行
        return chain.filter(serverWebExchange);
    }

    //遍历配置文件中需要验证的路径的
    private boolean isExclude(String path) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if(antPathMatcher.match(pathPattern,path)) {
                return true;
            }
        }
        return false;
    }

    //判断是否为登录请求
    private boolean isLogin(String path){
        return antPathMatcher.match("/user/login", path);
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
