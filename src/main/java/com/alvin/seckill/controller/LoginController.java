package com.alvin.seckill.controller;

import com.alvin.seckill.common.resultBean.MyResult;
import com.alvin.seckill.service.LoginService;
import com.alvin.seckill.pojo.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public MyResult<Boolean> login(HttpServletResponse response, HttpSession session, LoginVo loginVo, Model model)
    {
        /* ========================================================================================================= */
        /* Shiro 部分 */
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        loginService.passTrans(loginVo);
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getMobile(), loginVo.getPassword());

        loginService.login(response, session, loginVo);
        try{
            subject.login(token);
        }catch (UnknownAccountException e) {
            model.addAttribute("msg","UserErr");
            return MyResult.build();
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "Pwd Err");
            return MyResult.build();
        }

        /* ========================================================================================================= */
        MyResult<Boolean> result = MyResult.build();
        session.setAttribute("user", loginVo.getMobile());
        // loginService.login(response, session, loginVo);
        return result;
    }

/*
    @RequestMapping("/login")
    public MyResult<Boolean> login(HttpServletResponse response, HttpSession session, LoginVo loginVo, Model model)
    {
        MyResult<Boolean> result = MyResult.build();
        loginService.login(response, session, loginVo);
        return result;
    }*/
}
