package com.by.service;

import com.by.model.Role;
import com.by.model.RpVO;
import com.by.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by gcq on 2019/7/1.
 */
public interface RoleService {
    List<Role> findAll();

    void add(Role role);

    void del(Integer roleId);

    Role seleteOne(Integer id);

    void update(Role role);

    Map<String, Object> rolePermission(Integer roleId);

    void rolePer(RpVO rpVO);
}
