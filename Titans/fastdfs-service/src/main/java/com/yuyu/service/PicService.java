package com.yuyu.service;


import com.yuyu.entity.RcPic;

import java.util.List;

public interface PicService {
    List<RcPic> findListByUsername(String username);
    public void save(RcPic pic);
}
