package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;
    private User user;

    @Autowired
    public UserController(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    @GetMapping("user/list")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("user/add")
    public String add(Model model){
        model.addAttribute("user",user);
        return "users/add";
    }

}
