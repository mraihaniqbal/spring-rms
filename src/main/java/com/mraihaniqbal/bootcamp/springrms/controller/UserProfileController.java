package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.entity.UserProfile;
import com.mraihaniqbal.bootcamp.springrms.enums.Authority;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import com.mraihaniqbal.bootcamp.springrms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
        UserProfile profile = userProfileService.findByUsername(username);
        if(profile == null){
            return "error/404";
        }

        model.addAttribute("username", username);
        model.addAttribute("profile", profile);
        return "users/profile/index";
    }

    @GetMapping("/{username}/edit")
    public String edit(Model model, @PathVariable String username, Principal principal,
                       HttpServletRequest request){

        //User only allowed to update of itself, except Admin
        if(!request.isUserInRole(Authority.ROLE_ADMIN.toString())
                && !username.equals(principal.getName())){
            return "error/404";
        }

        UserProfile profile = userProfileService.findByUsername(username);
        if(profile == null){
            return "error/404";
        }

        model.addAttribute("profile", profile);
        model.addAttribute("username", username);
        return "/users/profile/form";
    }

    @PostMapping("/{username}/edit")
    public String editSubmit(@PathVariable String username, @Valid UserProfile userProfile, BindingResult bindingResult,
                             RedirectAttributes redir, Model model){
        //validate form
        if(bindingResult.hasErrors()){
            model.addAttribute("message","Please check again your form");
            return "users/profile/form";
        }

        //save update
        ResponseMap responseMap = userProfileService.edit(userProfile);

        //redirect
        redir.addFlashAttribute("response",responseMap);

        if(responseMap.isSuccess()){
            return "redirect:/user/list";
        }

        return "redirect:/"+username;
    }

}
