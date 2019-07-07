package com.by.service.impl;

import com.by.mapper.PermissionMapper;
import com.by.mapper.RoleMapper;
import com.by.mapper.RpMapper;
import com.by.model.Permission;
import com.by.model.Role;
import com.by.model.RpVO;
import com.by.model.User;
import com.by.service.PermissionService;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gcq on 2019/7/1.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RpMapper rpMapper;

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

    @Override
    public Map<String, Object> rolePermission(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        //查询全部的permissions
        List<Permission> permissions = permissionMapper.findAll();
        //通过id查permission对象,默认选中
        List<Integer> permissionids = permissionMapper.permissionId(roleId);

        map.put("permissions",permissions);
        map.put("permissionids",permissionids);

        return map;
    }

    @Override
    public void rolePer(RpVO rpVO) {
        //删除有关roleId的数据
        rpMapper.delete(rpVO.getRoleId());
        //再添加
        rpMapper.addpers(rpVO);
    }
}
