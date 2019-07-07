package com.by.Vo;

import com.by.model.Permission;
import com.by.model.Role;
import lombok.Data;

import java.util.List;

/**
 * Created by gcq on 2019/7/5.
 */
@Data
public class RoleVO extends Role {

    private List<Permission> permissions;
}
