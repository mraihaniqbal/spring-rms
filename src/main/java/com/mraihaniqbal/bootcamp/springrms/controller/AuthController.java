package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import com.mraihaniqbal.bootcamp.springrms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "/login";
    }

    @PostMapping("/login-submit")
    public String submitLogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
                              RedirectAttributes redirectAttributes, HttpSession session){

        ResponseMap responseMap = authService.login(username,password, session);

        String path = "redirect:/";
        if(!responseMap.isSuccess()){
            path = "redirect:/login";
        }

        redirectAttributes.addAttribute("message", responseMap.getMessage());
        return path;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        ResponseMap responseMap = authService.logout(session);
        redirectAttributes.addAttribute("message", responseMap.getMessage());

        return "redirect:/login";
    }
}
