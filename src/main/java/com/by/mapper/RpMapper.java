package com.by.mapper;

import com.by.model.Rp;
import com.by.model.RpVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RpMapper {
    int insert(Rp record);

    int insertSelective(Rp record);

    @Delete("delete from rp_id where role_id = #{roleId}")
    void delete(Integer roleId);

    void addpers(@Param("rpvos") RpVO rpVO);
}