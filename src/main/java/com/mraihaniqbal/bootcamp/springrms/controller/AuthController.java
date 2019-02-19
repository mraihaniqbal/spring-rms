package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import com.mraihaniqbal.bootcamp.springrms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/login-submit")
    public String submitLogin(@RequestBody String username, @RequestBody String password,
                              RedirectAttributes redirectAttributes, HttpSession session){

        ResponseMap responseMap = authService.login(username,password, session);

        String path = "redirect:/";
        if(!responseMap.isSuccess()){
            path = "redirect:/login";
        }

        redirectAttributes.addAttribute("responseMap", responseMap);
        return path;
    }

    @GetMapping("/logout")
    public String logout(){
        authService.logout();
        return "/";
    }
}
