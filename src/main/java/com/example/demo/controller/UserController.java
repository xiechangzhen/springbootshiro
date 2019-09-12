package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello");
        return "ok";

    }

    @RequestMapping("/themleaf")
    public String testThemleaf(Model model){
        //把数据存入model
        model.addAttribute("name","黑马");
        System.out.println("hello");
        //返回test.html
        return "test";
    }

    @RequestMapping("/add")
    public String add(Model model){
        System.out.println("add");
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(Model model){
        System.out.println("update");
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        System.out.println("login");
        return "/login";
    }


    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        System.out.println("name="+name);
        /**
         * 使用编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3.执行登录方法
        try {
            subject.login(token);
            return "redirect:/themleaf"; //成功时，重定向 不带值
        } catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "/login";
        }
    }

    @RequestMapping("/unAuth")
    public String toLogin(){
        System.out.println("unAuth");
        return "/unAuth";
    }



}
