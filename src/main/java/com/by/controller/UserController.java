package com.by.controller;


import com.by.Vo.RoleVO;
import com.by.Vo.User3VO;
import com.by.model.UrVO;
import com.by.model.User;
import com.by.model.UserVO;
import com.by.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gcq on 2019/7/1.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("list")
    public Map<String,Object> list(Integer page,Integer limit){

        //PageHelper.startPage(page,limit);
        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("start",(page-1)*limit);
        pagemap.put("limit",limit);
        List<User3VO> users =userService.findAll(pagemap);

        //PageInfo<User3VO> info = new PageInfo<>(users);

        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",50);
        map.put("data",users);


        return map;
    }


    @ResponseBody
    @RequestMapping("list2")
    public Map<String,Object> list2(Integer page,Integer limit){

        //PageHelper.startPage(page,limit);
        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("start",(page-1)*limit);
        pagemap.put("limit",limit);
        List<RoleVO> roles =userService.roleAndPermission(pagemap);

        //PageInfo<User3VO> info = new PageInfo<>(users);

        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",50);
        map.put("data",roles);


        return map;
    }

    @ResponseBody
    @DeleteMapping("delete/{id}")
    public String delect(@PathVariable("id")Integer id){


        try {
            userService.del(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ResponseBody
    @RequestMapping("userRole")
    public Map<String,Object> userRole(UrVO urVO){
        System.out.println(urVO);
        Map<String, Object> map = new HashMap<>();
        try {
            userService.userRole(urVO);

            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }

        return map;
    }





    @ResponseBody
    @RequestMapping("quan")
    public List<UserVO> quan(){
        List<UserVO> list = userService.list();
        return list;
    }

    @ResponseBody
    @RequestMapping("users")
    public List<User> list(){
        List<User> list =userService.finduserAll();

        return list;
    }

    @ResponseBody
    @PostMapping("user")
    public String add(User user){
        //System.out.println(user.getUserName());
        //System.out.println(user.getUserPswd());
        try {
            userService.add(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @ResponseBody
    @DeleteMapping("user")
    public String del (Integer userId){
        try {
            userService.del(userId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ResponseBody
    @GetMapping("user/{id}")
    public User seleteOne(@PathVariable("id") Integer id){
        User user=userService.seleteOne(id);
        return user;
    }


    @ResponseBody
    @PutMapping("user")
    public String upd(User user){

        try {
            userService.update(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

//shiro
    @RequestMapping("login")
    public String login(@RequestParam("userName")String userName, @RequestParam("pswd")String  pswd, ModelMap map){

        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(userName,pswd);
            token.setRememberMe(true);
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                //System.out.println("登陆失败：户名不存在");
                map.put("success","登陆失败：户名不存在");
                return "forward:/login.jsp";

            } catch (IncorrectCredentialsException e){
                //System.out.println("密码错误");
                map.put("success","密码错误");
                return "forward:/login.jsp";
            } catch (AuthenticationException e){
                System.out.println("登陆失败");
            }
        }
        return "redirect:/test.jsp";
    }

    @RequestMapping("loginadd")
    public String loginadd(@RequestParam("userName")String userName, @RequestParam("pswd")String  pswd, ModelMap map){
        map.put("success","不写不让进");
        if (userName!=null && !userName.equals("") && pswd!=null && !pswd.equals("")){
            User user = userService.findByName(userName);
            map.put("success","该账户已存在");
            if (user == null){
                User user1 = new User();
                user1.setUserName(userName);
                String md5 = String.valueOf(new SimpleHash("MD5", pswd,userName , 3));
                user1.setUserPswd(md5);
                userService.add(user1);
                map.put("success","注册成功");
                return "forward:/login.jsp";
            }
        }

       // SimpleHash md5 = new SimpleHash("MD5", pswd,userName , 3);


        return "forward:/loginadd.jsp";
    }




    @RequestMapping("toquanpage")
    public String toquan(){
        return "ui/quan";
    }

    @RequestMapping("tolistpage")
    public String tolist(){
        return "ui/list";
    }

    @RequestMapping("toselectpage")
    public String select(){
        return "ui/select";
    }
}
