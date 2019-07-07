package com.by.model;

import lombok.Data;

import java.util.List;

/**
 * Created by gcq on 2019/7/6.
 */
@Data
public class RpVO {

    private Integer roleId;

    private List<Integer> permissions;
}
