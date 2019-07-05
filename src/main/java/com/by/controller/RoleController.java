package com.by.controller;


import com.by.model.Role;
import com.by.model.User;
import com.by.service.RoleService;
import com.by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gcq on 2019/7/1.
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("roles")
    public List<Role> list(){
        List<Role> list =roleService.findAll();

        return list;
    }

    @ResponseBody
    @PostMapping("role")
    public String add(Role role){

        try {
            roleService.add(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @ResponseBody
    @DeleteMapping("role")
    public String del (Integer roleId){
        try {
            roleService.del(roleId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ResponseBody
    @GetMapping("role/{id}")
    public Role seleteOne(@PathVariable("id") Integer id){
        Role role=roleService.seleteOne(id);
        return role;
    }


    @ResponseBody
    @PutMapping("role")
    public String upd(Role role){

        try {
            roleService.update(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }






    @RequestMapping("torolepage")
    public String select(){
        return "ui/role";
    }
}
