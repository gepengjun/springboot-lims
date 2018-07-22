package com.gepengjun.lims.controller;

import com.gepengjun.lims.service.UserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
import java.util.HashMap;
import java.util.Map;

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
        String validateCode = myKaptcha.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,validateCode);
        BufferedImage bufferedImage = myKaptcha.createImage(validateCode);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ImageIO.write(bufferedImage,"jpg",servletOutputStream);
        try {
            servletOutputStream.flush();
        }finally {
            servletOutputStream.close();

        }
        return null;
    }

    @RequestMapping(value = "/ajaxLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> ajaxLogin(HttpServletRequest request,String username,String password,String kaptchaCode){
        Map<String,String> result = new HashMap<>();
        String validateCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptchaCode == null || "".equals(kaptchaCode)){
            result.put("status","0");
            result.put("msg","验证码不能为空");
            return result;
        }
        if (!kaptchaCode.equalsIgnoreCase(validateCode)){
            result.put("status","0");
            result.put("msg","验证码不正确");
            return result;
        }
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
            System.out.println("开始登录---------");
            SecurityUtils.getSubject().login(usernamePasswordToken);
            System.out.println("登录成功---------");
            result.put("status","1");
            result.put("msg","登录成功");
        }catch (UnknownAccountException uae){
            result.put("status","0");
            result.put("msg","账号或密码不正确");
        }catch (IncorrectCredentialsException inc){
            result.put("status","0");
            result.put("msg","账号或密码不正确");
        }
        System.out.println("return--result-------");
        return  result;
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        System.out.println("login----------login-------");
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
