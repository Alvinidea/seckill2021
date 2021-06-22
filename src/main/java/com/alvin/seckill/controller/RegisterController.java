package com.alvin.seckill.controller;

import com.alvin.seckill.common.resultBean.MyResult;
import com.alvin.seckill.pojo.LoginVo;
import com.alvin.seckill.service.UserService;
import com.alvin.seckill.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public MyResult<String> doRegister(@RequestParam("username") String userName,
                                       @RequestParam("password") String passWord,
                                       @RequestParam("verifyCode") String verifyCode,
                                       @RequestParam("salt") String salt,HttpServletResponse response )
    {
        MyResult<String> result = MyResult.build();
        boolean check = verifyCodeService.checkVerifyCodeRegister(Integer.valueOf(verifyCode));
        if(! check){
            result.withError(200002,"验证码不一致!");
            return result;
        }
        boolean registerInfo = userService.register(response, userName, passWord, salt);
        if(! registerInfo)
        {
            result.withError(200001, "注册失败！");
            return result;
        }
        return result;
    }


    @RequestMapping(value = "/verifyCodeRegister", method = RequestMethod.GET)
    @ResponseBody
    public MyResult<String> getMiaoshaVerifyCod(HttpServletResponse response) {
        MyResult<String> result = MyResult.build();
        try {
            BufferedImage image = verifyCodeService.createVerifyCodeRegister();
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return result;
        } catch (Exception e) {
            // logger.error("生成验证码错误-----注册:{}", e);
            // result.withError(MIAOSHA_FAIL.getCode(), MIAOSHA_FAIL.getMessage());
            return result;
        }
    }
}
