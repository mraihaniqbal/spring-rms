package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

}
