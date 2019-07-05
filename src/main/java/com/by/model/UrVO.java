package com.by.model;

import lombok.Data;

import java.util.List;

/**
 * Created by gcq on 2019/7/2.
 */
@Data
public class UrVO {
    private Integer userId;

    private List<Integer> roles;
}
