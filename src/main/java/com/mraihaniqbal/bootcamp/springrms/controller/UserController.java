package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import com.mraihaniqbal.bootcamp.springrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @PostMapping("user/add")
    public String addSubmit(@Valid User user, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model){
        //validate form
        if(bindingResult.hasErrors()){
            System.out.println("test");
            bindingResult.getAllErrors().forEach(System.out::println);
            model.addAttribute("response",new ResponseMap(false,"Please check again your form"));
            return "users/add";
        }

        //do the add thing
        ResponseMap responseMap = userService.add(user);

        //redirect
        redirectAttributes.addFlashAttribute("response",responseMap);

        if(responseMap.isSuccess()){
            return "redirect:/user/list";
        }

        return "redirect:/user/add";
    }

}
