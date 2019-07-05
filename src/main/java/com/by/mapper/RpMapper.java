package com.by.mapper;

import com.by.model.Rp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RpMapper {
    int insert(Rp record);

    int insertSelective(Rp record);
}