package com.controller;

import com.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        System.out.println("getLoginPage");
        return "login";//将跳转给下面的post请求
    }
    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
//        System.out.println("postLoginPage");
        //只要账密密码不为空就登录成功
        if(user.getUserName().equals("admin")&&user.getPassword().equals("123456")){
            System.out.println("登录成功");
            session.setAttribute("loginUser",user);
            return "redirect:main.html";
        }
        else{
            //如果不成功，提示信息
            model.addAttribute("msg","账户密码不正确");
            return "login";
        }
    }
    @GetMapping("main.html")
    public String RedirectMain(HttpSession session,Model model){
        if(session.getAttribute("loginUser")==null||session.getAttribute("loginUser").equals("")){
            model.addAttribute("msg","请先登录");
            return "login";
        }else{
            return "main";
        }

    }
}
