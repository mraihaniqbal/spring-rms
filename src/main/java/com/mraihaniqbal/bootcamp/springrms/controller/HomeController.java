package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.enums.Authority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String gate(Authentication auth, HttpServletRequest request){
        if(request.isUserInRole(Authority.ROLE_ADMIN.toString())){
            return "redirect:user/list";
        }
        if(request.isUserInRole(Authority.ROLE_PM.toString())){
            return "redirect:project/list";
        }

        return "redirect:project/list/"+auth.getName();
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "error/403";
    }

}
