package com.ds.api.client;

import com.ds.common.domain.R;
import com.ds.common.enums.UserLevel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("file-service")
public interface FileClient {
    @PostMapping(value="/file/doc",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R<UserLevel> uploadDoc(@RequestPart("file") MultipartFile file, @RequestParam("uuid") String uuid);
}
