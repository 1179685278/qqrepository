package com.by.controller;

import com.by.model.Ur;
import com.by.service.PermissionService;
import com.by.service.RoleService;
import com.by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gcq on 2019/7/2.
 */
@Controller
@RequestMapping("quan")
public class QuanController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("quan")
    public Map<String,Object> quan(Integer userId){
        Map<String, Object> map = new HashMap<>();
        map = userService.userRoles(userId);
        return map;
    }

    @ResponseBody
    @RequestMapping("role")
    public String del (Ur ur){

        try {
            Ur ur1 = userService.selectone(ur.getUserId());
            if (ur1 == null){
                userService.uradd(ur);
            }else {
                userService.urupdate(ur);
            }

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
