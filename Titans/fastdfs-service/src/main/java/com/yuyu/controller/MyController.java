package com.yuyu.controller;

import com.yuyu.entity.RcPic;
import com.yuyu.service.FastDFSClientWrapper;
import com.yuyu.service.PicService;
import com.yuyu.vo.FastDFSVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MyController {

    @Autowired
    private FastDFSClientWrapper dfsClient;

    @Autowired
    private PicService picService;

    // 上传图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public FastDFSVo upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 省略业务逻辑代码。。。
        String imgUrl = dfsClient.uploadFile(file);
        FastDFSVo vo=new FastDFSVo();
        vo.setGroupName(imgUrl.substring(0,imgUrl.indexOf("/")));
        vo.setRemoteFileName(imgUrl.substring(imgUrl.indexOf("/")+1));
        return vo;
    }

    @RequestMapping(value = "/download",method=RequestMethod.GET)
    public ResponseEntity<byte[]> download(String groupName,String remoteFileName,String fileName) throws IOException {
        byte[] body =  dfsClient.download(groupName,remoteFileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }

    @RequestMapping(value = "/toMain")
    public ModelAndView toMain(HttpServletRequest request){
        ModelAndView model= new ModelAndView("main");
        model.getModel().put("user","yuyu");
        return model;
    }
    @RequestMapping(value = "/toUpload")
    public ModelAndView toUpload(HttpServletRequest request){
        ModelAndView model= new ModelAndView("/upload/upload");
        model.getModel().put("user","yuyu");
        return model;
    }

    private static AtomicInteger conut=new AtomicInteger(1);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 省略业务逻辑代码。。。
        String imgUrl = dfsClient.uploadFile(file);

        RcPic pic=new RcPic();
        pic.setId(conut.getAndIncrement());
        pic.setPicPath(imgUrl);
        pic.setUserName("yuyu");
        this.picService.save(pic);


        ModelAndView model= new ModelAndView("forward:/toList");
        model.getModel().put("imgUrl",imgUrl);


        return model;
    }
    @RequestMapping(value = "/toList", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView toList(ModelAndView model) throws Exception {
        List<RcPic> picList=this.picService.findListByUsername("yuyu");

        model.setViewName("/upload/upload");
        model.getModel().put("pics",picList);
        return model;
    }

    }