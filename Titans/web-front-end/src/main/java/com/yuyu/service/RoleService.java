package com.yuyu.service;


import com.yuyu.entity.RcRoleEntity;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:12:30
 * ProjectName:Mirco-Service-Skeleton
 */
public interface RoleService {
    List<RcRoleEntity> getRoleValuesByUserId(Integer userId);
}
