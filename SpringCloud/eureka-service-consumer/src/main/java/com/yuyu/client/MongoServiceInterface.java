package com.yuyu.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public interface MongoServiceInterface {

    @RequestLine("POST /upload/{folder}")
    @Headers("Content-Type: multipart/form-data")
    public void fileUpload(@Param("folder") String folder, @Param("file") MultipartFile file);
}
