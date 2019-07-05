package com.by.controller;


import com.by.model.Permission;
import com.by.model.Role;
import com.by.service.PermissionService;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gcq on 2019/7/1.
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping("permissions")
    public List<Permission> list(){
        List<Permission> list =permissionService.findAll();

        return list;
    }

    @ResponseBody
    @PostMapping("permission")
    public String add(Permission permission){

        try {
            permissionService.add(permission);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @ResponseBody
    @DeleteMapping("permission")
    public String del (Integer permissionId){
        try {
            permissionService.del(permissionId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ResponseBody
    @GetMapping("permission/{id}")
    public Permission seleteOne(@PathVariable("id") Integer id){
        Permission permission=permissionService.seleteOne(id);
        return permission;
    }


    @ResponseBody
    @PutMapping("permission")
    public String upd(Permission permission){

        try {
            permissionService.update(permission);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }






    @RequestMapping("topermissionpage")
    public String select(){
        return "ui/permission";
    }
}
