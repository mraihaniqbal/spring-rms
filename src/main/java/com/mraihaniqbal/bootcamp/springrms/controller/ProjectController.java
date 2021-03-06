package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.entity.Project;
import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import com.mraihaniqbal.bootcamp.springrms.service.ProjectService;
import com.mraihaniqbal.bootcamp.springrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private Project project;
    private UserService userService;
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService, Project project, UserService userService) {
        this.project = project;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("projects", projectService.getList());
        model.addAttribute("title","Project List");
        return "project/list";
    }

    @GetMapping("/list/{username}")
    public String listByUsername(Model model, @PathVariable String username, Principal principal){
        User user = userService.findByUsername(username);
        if(user == null){
            return "error/404";
        }

        String name = user.getUserProfile().getFirstName()+"'s";
        if(username.equals(principal.getName())){
            name = "My";
        }

        model.addAttribute("user",user);
        model.addAttribute("projects", projectService.getList(username));
        model.addAttribute("title",name+" Project List");

        return "project/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("project",project);
        model.addAttribute("action","add");
        return "project/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        Project project = projectService.findById(id);
        if(project == null){
            return "error/404";
        }
        model.addAttribute("project",project);
        model.addAttribute("action","edit");
        return "project/form";
    }

    @PostMapping("/{action}/submit")
    public String submit(@Valid Project project, BindingResult bindingResult,
                         RedirectAttributes redir, Model model, @PathVariable String action){

        //validate form
        if(bindingResult.hasErrors()){
            model.addAttribute("response", new ResponseMap(false,"Please check again your form"));
            model.addAttribute("action",action);
            return "/project/form";
        }

        //submit
        ResponseMap responseMap = projectService.save(project);

        //redirect
        redir.addFlashAttribute("response",responseMap);
        if(responseMap.isSuccess()){
            return "redirect:/project/detail/"+responseMap.getReturnedId();
        }

        return "redirect:project/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id){
        Project project = projectService.findById(id);
        if(project == null){
            return "error/404";
        }
        model.addAttribute("project",project);
        model.addAttribute("users",userService.findByUsernameNotIn(project.getUsers()));

        return "project/detail";
    }

    @PostMapping("/{id}/add-member")
    public String addMember(@PathVariable Long id, String username, RedirectAttributes redir){
        ResponseMap responseMap = projectService.addMember(id,username);
        redir.addFlashAttribute("message",responseMap.getMessage());

        return "redirect:/project/detail/"+id;
    }

    @GetMapping("/{id}/remove-member/{userId}")
    public String removeMember(@PathVariable Long id, @PathVariable Long userId, RedirectAttributes redir){
        ResponseMap responseMap = projectService.removeMember(id,userId);
        redir.addFlashAttribute("message",responseMap.getMessage());

        return "redirect:/project/detail/"+id;
    }



}
