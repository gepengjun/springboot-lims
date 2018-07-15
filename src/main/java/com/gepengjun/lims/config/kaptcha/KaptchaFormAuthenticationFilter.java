package com.gepengjun.lims.config.kaptcha;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class KaptchaFormAuthenticationFilter extends FormAuthenticationFilter{

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String validateCode =(String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String kaptchaCode = httpServletRequest.getParameter("kaptchaCode");

        if (validateCode != null && kaptchaCode != null && !validateCode.equalsIgnoreCase(kaptchaCode)){
            httpServletRequest.setAttribute("shiroLoginFailure","kaptchaValidateFailed");
            return true;
        }
        return super.onAccessDenied(request,response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
       WebUtils.getAndClearSavedRequest(request);
       WebUtils.redirectToSavedRequest(request,response,this.getSuccessUrl());
        return false;
    }


}
