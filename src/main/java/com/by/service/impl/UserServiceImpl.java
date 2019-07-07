package com.by.service.impl;

import com.by.Vo.RoleVO;
import com.by.Vo.User3VO;
import com.by.mapper.RoleMapper;
import com.by.mapper.UrMapper;
import com.by.mapper.UserMapper;
import com.by.model.*;
import com.by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gcq on 2019/7/1.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UrMapper urMapper;

    @Override
    public List<UserVO> list() {
        return userMapper.list();
    }

    @Transactional
    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public List<User3VO> findAll(Map<String, Object> pagemap) {
        return userMapper.findAll(pagemap);
    }

    @Override
    public void del(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User seleteOne(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Map<String, Object> userRoles(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        //查询全部的roles
        List<Role> roles = roleMapper.findAll();
        //通过id查role对象,默认选中
        List<Integer> roleids = userMapper.roleId(userId);

        map.put("roles",roles);
        map.put("roleids",roleids);

        return map;
    }

    @Override
    public void uradd(Ur ur) {
        urMapper.insertSelective(ur);
    }

    @Override
    public Ur selectone(Integer userId) {
        return urMapper.selectone(userId);
    }

    @Override
    public void urupdate(Ur ur) {
        urMapper.urupdate(ur);
    }

    @Override
    public void userRole(UrVO urVO) {
        //删除有关userId的数据
        urMapper.delete(urVO.getUserId());

        //再添加
        urMapper.addroles(urVO);
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public Set<String> findRoleByUserName(String username) {
        return userMapper.findRoleByUserName(username);
    }

    @Override
    public Set<String> findPermissionByUserName(String username) {
        return userMapper.findPermissionByUserName(username);
    }

    @Override
    public List<User> finduserAll() {
        return userMapper.finduserAll();
    }

    @Override
    public List<RoleVO> roleAndPermission(Map<String, Object> pagemap) {
        return userMapper.roleAndPermission(pagemap);
    }


}
