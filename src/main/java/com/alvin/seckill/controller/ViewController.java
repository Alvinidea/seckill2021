package com.alvin.seckill.controller;

import com.alvin.seckill.service.GoodsService;
import com.alvin.seckill.pojo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewController extends BaseController{

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/login")
    public String loginView()
    {
        return "login";
    }


    @RequestMapping("/register")
    public String register()
    {
        return "register";
    }


    @RequestMapping("/goods_list")
    public String goods_list(HttpSession session, Model model)
    {

        model.addAttribute("user", session.getAttribute("user"));
        List<GoodsVo> goodsVoList = goodsService.getGoodsVoList();
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";
    }


    @RequestMapping("/goods_detail/{goodsId}")
    public String goods_detail(@PathVariable("goodsId") long goodsId, Model model)
    {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goodsVo",goodsVo);
        model.addAttribute("miaoshaStatus",0);
        model.addAttribute("remainSeconds",3600);
        return "goods_detail";
    }


    /*
    *  Shiro测试时候的代码
    * */
    @RequestMapping("/unAuthorize")
    @ResponseBody
    public String unAuthorize(){
        return "Shiro相关的未授权页面！！！";
    }

    @RequestMapping("/shiroAuth")
    @ResponseBody
    public String shiroAuth(){
        return "shiroAuth shiroAuth shiroAuth shiroAuth";
    }
}
