package com.jack.controller;

import com.jack.model.UsersDto;
import com.jack.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private LoginService _service;

    @GetMapping
    public String RegisterPage(Model model){
        model.addAttribute("usersDto", new UsersDto());
        return "register";
    }

    @PostMapping
    public String Post(@Valid UsersDto usersDto, BindingResult result){
        if(!usersDto.ValidPassword()){
            result.rejectValue("confirmPassword", "validPswError", "與密碼不一致");
        }

        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError e: errors) {
                String msg = e.getField() + ": "
                        + e.getDefaultMessage() + ": "
                        + e.getCode();
                System.out.println(msg);
            }
            return "register";
        }
        _service.Insert(usersDto);
        return "redirect:/login";
    }
}
