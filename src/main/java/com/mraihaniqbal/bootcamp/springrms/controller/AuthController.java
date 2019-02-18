package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/auth/login";
    }

    @PostMapping("/login")
    public String submitLogin(@RequestBody String username, @RequestBody String password){
        authService.login(username,password);
        return "/";
    }

    @GetMapping("/logout")
    public String logout(){
        authService.logout();
        return "/";
    }
}
