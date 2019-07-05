package com.by.service.impl;

import com.by.mapper.RoleMapper;
import com.by.model.Role;
import com.by.model.User;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gcq on 2019/7/1.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void add(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void del(Integer roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public Role seleteOne(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
