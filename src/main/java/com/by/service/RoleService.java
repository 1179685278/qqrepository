package com.by.service;

import com.by.model.Role;
import com.by.model.User;

import java.util.List;

/**
 * Created by gcq on 2019/7/1.
 */
public interface RoleService {
    List<Role> findAll();

    void add(Role role);

    void del(Integer roleId);

    Role seleteOne(Integer id);

    void update(Role role);
}
