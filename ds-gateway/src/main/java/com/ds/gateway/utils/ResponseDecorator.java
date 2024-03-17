package com.ds.gateway.utils;


import cn.hutool.json.JSONObject;
import com.ds.gateway.config.JwtProperties;
import com.ds.gateway.utils.JwtTool;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.time.Duration;

/**
 * @author writiger
 * @description 重写登录返回值
 * @create_at 2024-03-17 13:10
 */
public class ResponseDecorator extends ServerHttpResponseDecorator {
    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final Duration TTL;

    public ResponseDecorator(ServerHttpResponse delegate,JwtTool jwtTool,Duration ttl,PasswordEncoder passwordEncoder){
        super(delegate);
        this.jwtTool = jwtTool;
        this.TTL = ttl;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @SuppressWarnings(value = "unchecked")
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

        if(body instanceof Flux) {
            Flux<DataBuffer> fluxBody = (Flux<DataBuffer>) body;

            return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                DataBuffer join = dataBufferFactory.join(dataBuffers);

                byte[] content = new byte[join.readableByteCount()];
                join.read(content);
                DataBufferUtils.release(join);// 释放掉内存

                String bodyStr = new String(content, Charset.forName("UTF-8"));

                //修改响应体
                bodyStr = modifyBody(bodyStr);

                getDelegate().getHeaders().setContentLength(bodyStr.getBytes().length);
                return bufferFactory().wrap(bodyStr.getBytes());
            }));
        }
        return super.writeWith(body);
    }


    private String modifyBody(String jsonStr){
        JSONObject json = new JSONObject(jsonStr);
        //1. 验证密码是否匹配
        JSONObject json2 = new JSONObject(json.get("data"));
        String in = (String) json2.get("inPasswd");
        String out = (String) json2.get("outPasswd");
        if (!passwordEncoder.matches(in,out)){
            json.set("code", HttpStatus.BAD_REQUEST.value());
            json.set("msg","密码或账号错误");
            json.remove("data");
            return json.toString();
        }
        Long id = Long.parseLong(json2.get("id").toString());
        //2. 生成token
        String token = jwtTool.createToken(id,TTL);
        json2.remove("id");
        json2.remove("inPasswd");
        json2.remove("outPasswd");
        json2.set("token",token);
        json.set("data",json2);
        //3. 返回
        return json.toString();
    }
}
