package com.mraihaniqbal.bootcamp.springrms.controller;

import com.mraihaniqbal.bootcamp.springrms.entity.Project;
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
        return "project/list";
    }

    @GetMapping("/list/{username}")
    public String listByUsername(Model model, @PathVariable String username){
        model.addAttribute("user",userService.findByUsername(username));
        model.addAttribute("projects", projectService.getList(username));
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
            return "404";
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
            System.out.println("hai");
            bindingResult.getAllErrors().forEach(System.out::println);
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
            return "404";
        }
        model.addAttribute("project",project);
        return "project/detail";
    }

    @GetMapping("/detail/{id}/add-member")
    public String addMember(Model model, @PathVariable Long id){
        Project project = projectService.findById(id);
        if(project == null){
            return "404";
        }
        model.addAttribute("project",project);
        return "project/addMember";
    }

    @PostMapping("/detail/{id}/add-member")
    public String submitAddMember(@PathVariable Long id){
        return "redirect:/detail/"+id;
    }



}
