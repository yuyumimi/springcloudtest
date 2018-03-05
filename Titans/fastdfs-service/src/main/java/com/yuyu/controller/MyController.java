package com.yuyu.controller;

import com.yuyu.service.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyController {

    @Autowired
    private FastDFSClientWrapper dfsClient;

    // 上传图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 省略业务逻辑代码。。。
        String imgUrl = dfsClient.uploadFile(file);
        // 。。。。
        return "success";

    }
    @RequestMapping(value = "/down", method = RequestMethod.POST)
    public String down(String file) throws Exception {
        // 省略业务逻辑代码。。。
        dfsClient.download();
        // 。。。。
        return "success";

    }

}