package com.by.service;

import com.by.Vo.RoleVO;
import com.by.Vo.User3VO;
import com.by.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gcq on 2019/7/1.
 */
public interface UserService {
    List<UserVO> list();

    void add(User user);

    List<User3VO> findAll(Map<String, Object> pagemap);


    void del(Integer userId);

    User seleteOne(Integer id);

    void update(User user);


    Map<String, Object> userRoles(Integer userId);

    void uradd(Ur ur);

    Ur selectone(Integer userId);

    void urupdate(Ur ur);

    void userRole(UrVO urVO);

    User findByName(String username);

    Set<String> findRoleByUserName(String username);

    Set<String> findPermissionByUserName(String username);

    List<User> finduserAll();

    List<RoleVO> roleAndPermission(Map<String, Object> pagemap);
}
