package com.gepengjun.lims.controller;

import com.gepengjun.lims.service.UserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Autowired
    private DefaultKaptcha myKaptcha;


    @RequestMapping(value = "/kaptcha",method = RequestMethod.GET)
    @ResponseBody
    public String kaptcha(HttpServletRequest request, HttpServletResponse response,ModelMap map) throws IOException{
        response.setHeader("Expires","0");
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");
        String validateText = myKaptcha.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,validateText);
        BufferedImage bufferedImage = myKaptcha.createImage(validateText);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ImageIO.write(bufferedImage,"jpg",servletOutputStream);
        try {
            servletOutputStream.flush();
        }finally {
            servletOutputStream.close();

        }
        return null;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, ModelMap map){
        System.out.println("login---------");
        System.out.println("login------request---"+request.getSession().getAttribute("KAPTCHA_SESSION_KEY"));
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
    public String login(HttpServletRequest request){

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
