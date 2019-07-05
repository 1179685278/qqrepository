package com.by.mapper;

import com.by.model.User;
import com.by.Vo.User3VO;
import com.by.model.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    List<UserVO> list();


    List<User3VO> findAll(Map<String, Object> pagemap);

    @Select("select role_id from ur_id where user_id = #{iserId}")
    List<Integer> roleId(Integer userId);

    @Select("select * from t_user where user_name = #{username}")
    User findByName(String username);

    @Select("select r.role_name from t_user u,t_role r,ur_id ur where u.user_id = ur.user_id and ur.role_id = r.role_id and u.user_name = #{username}")
    Set<String> findRoleByUserName(String username);

    Set<String> findPermissionByUserName(String username);

    @Select("select * from t_user")
    List<User> finduserAll();
}