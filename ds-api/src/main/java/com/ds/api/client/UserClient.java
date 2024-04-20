package com.ds.api.client;

import com.ds.common.domain.R;
import com.ds.common.enums.UserLevel;
import com.ds.common.exception.CommonException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/level/{userId}")
    R<UserLevel> getLevel(@PathVariable("userId") Long userId);
}
