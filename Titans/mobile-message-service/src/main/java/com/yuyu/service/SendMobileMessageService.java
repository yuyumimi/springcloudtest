package com.yuyu.service;

import com.yuyu.bo.OpenLock;
import com.yuyu.dao.OpenLockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "sendMobileMessageService")
public class SendMobileMessageService {

    @Autowired
    private OpenLockRepository openLockRepository;

    public OpenLock queryOpenLockByCode(String code) {
        // 从数据库中查询符合条件的起止号码段
        // 根据前7位进行号段验证
        List<OpenLock> openList = openLockRepository.findOpenLockByCode(code);
        if (openList != null && openList.size() > 0) {
            // 根据11位进行号段验证
            return openList.get(0);
        }
        return null;
    }

}
