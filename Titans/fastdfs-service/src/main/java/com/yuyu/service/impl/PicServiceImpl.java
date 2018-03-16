package com.yuyu.service.impl;

import com.yuyu.entity.RcPic;
import com.yuyu.repository.PicRepository;
import com.yuyu.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicServiceImpl implements PicService {
    @Autowired
    private PicRepository picRepository;

    @Override
    public List<RcPic> findListByUsername(String username) {
        return picRepository.findListByUsername(username);
    }

    public void save(RcPic pic){
        this.picRepository.save(pic);
    }
}
