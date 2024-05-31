package com.ds.api.client;

import com.ds.common.domain.R;
import com.ds.common.enums.UserLevel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("file-service")
public interface FileClient {
    @PostMapping(value="/file/doc",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R<UserLevel> uploadDoc(@RequestPart("file") MultipartFile file, @RequestParam("uuid") String uuid);

    @DeleteMapping("/file")
    void delete( @RequestParam("uuid") String uuid, @RequestParam("suffix") String suffix);
}
