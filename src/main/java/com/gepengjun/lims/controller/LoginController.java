package com.gepengjun.lims.controller;

import com.gepengjun.lims.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, ModelMap map){
        System.out.println("login---------");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null){
            if (UnknownAccountException.class.getName().equals(exception)){
                msg = "账号不正确";
            }else if (IncorrectCredentialsException.class.getName().equals(exception)){
                msg = "密码不正确";
            }else if ("kaptchaValidateFailed".equals(exception)){
                msg = "验证码不正确";
            }else {
                msg ="exception--"+exception;
            }
        }
        map.addAttribute("msg",msg);
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/unautho")
    public  String unautho(){
        return "unautho";
    }
}
