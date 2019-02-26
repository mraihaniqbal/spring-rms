package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.enums.Authority;
import com.mraihaniqbal.bootcamp.springrms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{username}")
    public String view(Model model, @PathVariable String username){
        model.addAttribute("profile", userProfileService.findByUsername(username));
        return "users/profile/index";
    }

    @GetMapping("/{username}/edit")
    public String edit(Model model, @PathVariable String username, Principal principal,
                       Authentication auth){

        //User only allowed to update of itself, except Admin
        if(!User.isAdmin(auth) && !username.equals(principal.getName())){
            return "404";
        }

        model.addAttribute("profile", userProfileService.findByUsername(username));
        return "/users/profile/form";
    }

    @PostMapping("/{username}/edit")
    public String editSubmit(@PathVariable String username){
        //save update

        return "redirect:/"+username;
    }

}
