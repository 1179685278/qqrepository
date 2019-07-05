package com.by.service.impl;

import com.by.mapper.PermissionMapper;
import com.by.model.Permission;
import com.by.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gcq on 2019/7/1.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public void add(Permission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void del(Integer permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public Permission seleteOne(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }
}
