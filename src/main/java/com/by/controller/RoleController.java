package com.by.controller;


import com.by.model.Role;
import com.by.model.RpVO;
import com.by.model.UrVO;
import com.by.model.User;
import com.by.service.RoleService;
import com.by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gcq on 2019/7/1.
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @DeleteMapping("delete/{id}")
    public String delect(@PathVariable("id")Integer id){


        try {
            roleService.del(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ResponseBody
    @GetMapping("quan")
    public Map<String,Object> quan(Integer roleId){
        Map<String, Object> map = new HashMap<>();
        map = roleService.rolePermission(roleId);
        return map;
    }


    @ResponseBody
    @RequestMapping("userRole")
    public Map<String,Object> userRole(RpVO rpVO){
        System.out.println(rpVO);
        Map<String, Object> map = new HashMap<>();
        try {
            roleService.rolePer(rpVO);

            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping("addrole")
    public Map<String,Object> addrole(String roleName){
        System.out.println(roleName);
        Map<String, Object> map = new HashMap<>();
        try {
            Role role = new Role();
            role.setRoleName(roleName);
            roleService.add(role);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }

        return map;
    }


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
