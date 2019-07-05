package com.by.service;

import com.by.model.Permission;

import java.util.List;

/**
 * Created by gcq on 2019/7/1.
 */
public interface PermissionService {
    List<Permission> findAll();

    void add(Permission permission);

    void del(Integer permissionId);

    Permission seleteOne(Integer id);

    void update(Permission permission);
}
