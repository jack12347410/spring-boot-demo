package com.jack.controller;

import com.jack.model.Users;
import com.jack.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService _service;

    @GetMapping
    public String LoginPage(){
        return "login";
    }

    @PostMapping
    public String Post(@RequestParam String userName,
                       @RequestParam String password,
                       HttpSession session){
        Users user  = _service.FindUser(userName, password);
        if(user != null){
            session.setAttribute("user", user);
            return "index";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @GetMapping("/exception")
    public String testException(){
        throw new RuntimeException("錯誤訊息");
    }
}
