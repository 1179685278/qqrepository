package com.by.Vo;

import com.by.model.Role;
import com.by.model.User;
import lombok.Data;

import java.util.List;

/**
 * Created by gcq on 2019/7/4.
 */
@Data
public class User3VO extends User {

    private List<Role> roles;
}
