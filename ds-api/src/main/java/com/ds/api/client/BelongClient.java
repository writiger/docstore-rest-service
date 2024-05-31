package com.ds.api.client;

import com.ds.common.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("belong-service")
public interface BelongClient {
    @GetMapping("/belong/exist/{name}")
    R<Boolean> isExist(@PathVariable(value="name") String name);
}
