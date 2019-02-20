package com.mraihaniqbal.bootcamp.springrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String gate(HttpSession session){

        return "redirect:user/list";
    }

}
