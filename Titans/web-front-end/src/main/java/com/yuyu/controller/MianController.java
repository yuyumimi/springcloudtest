package com.yuyu.controller;

import com.yuyu.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "/main")
public class MianController {

    private static final Logger LOGGER= LoggerFactory.getLogger(MianController.class);

    @RequestMapping(value = "/toMain")
    public ModelAndView toMain(HttpServletRequest request){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String ip=IPUtil.getReqIpAddr(request);
        LOGGER.info("用户{}，使用的浏览器ip为{}",userDetails.getUsername(),ip);
        ModelAndView model= new ModelAndView("main");
        model.getModel().put("user",userDetails.getUsername());
        return model;
    }
}
