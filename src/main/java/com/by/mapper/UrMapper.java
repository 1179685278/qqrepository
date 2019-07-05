package com.by.mapper;

import com.by.model.Ur;
import com.by.model.UrVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UrMapper {
    int insert(Ur record);

    int insertSelective(Ur record);

    @Select("select * from ur_id where user_id = #{userId}")
    Ur selectone(Integer userId);

    @Update("update ur_id set role_id = #{roleId} where user_id = #{userId}")
    void urupdate(Ur ur);

    @Delete("delete from ur_id where user_id = #{userId}")
    void delete(Integer userId);

    void addroles(@Param("urvos")UrVO urVO);
}