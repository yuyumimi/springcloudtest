package com.yuyu.service;

import com.yuyu.entity.RcMenuEntity;

import java.util.List;

public interface PermissionService {
    List<RcMenuEntity> getPermissionsByRoleId(Integer roleId);
}
