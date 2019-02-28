package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.ProjectDao;
import com.mraihaniqbal.bootcamp.springrms.entity.Project;
import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private ProjectDao projectDao;
    private ResponseMap responseMap;
    private UserService userService;

    @Autowired
    public ProjectService(ProjectDao projectDao, ResponseMap responseMap, UserService userService) {
        this.projectDao = projectDao;
        this.responseMap = responseMap;
        this.userService = userService;
    }

    public List<Project> getList(){
        return projectDao.findAll();
    }

    public List<Project> getList(String username){
        return projectDao.findByUsers_Username(username);
    }

    public Project findById(Long id){
        return projectDao.findById(id).orElse(null);
    }

    public ResponseMap save(Project project){
        try{
            Project savedProject = projectDao.save(project);
            responseMap.setSuccess(true);
            responseMap.setMessage("Success");
            responseMap.setReturnedId(savedProject.getId());
        }catch (DataAccessException e){
            responseMap.setSuccess(false);
            responseMap.setMessage("Data Error");
            e.printStackTrace();
        }
        return responseMap;
    }

    public ResponseMap addMember(Long id, String username){
        User user = userService.findByUsername(username);
        Project project = findById(id);

        if(project == null || user == null){
            responseMap.setMessage("Data Not Found");
            responseMap.setSuccess(false);
            return responseMap;
        }

        try{
            List<User> users = project.getUsers();
            if(users == null || users.isEmpty()){
                users = new ArrayList<>();
            }
            users.add(user);
            project.setUsers(users);
            projectDao.save(project);

            responseMap.setSuccess(true);
            responseMap.setMessage("Add Member Success");
        }catch (DataAccessException e){
            responseMap.setSuccess(false);
            responseMap.setMessage("Something wrong. Please contact your administrator.");
        }

        return responseMap;
    }

    public ResponseMap removeMember(Long id, Long userId){
        User user = userService.findById(userId);
        Project project = findById(id);

        if(project == null || user == null){
            responseMap.setMessage("Data Not Found");
            responseMap.setSuccess(false);
            return responseMap;
        }

        try{
            List<User> users = project.getUsers();
            users.remove(user);
            project.setUsers(users);
            projectDao.save(project);

            responseMap.setSuccess(true);
            responseMap.setMessage("Remove Member Success");
        }catch (DataAccessException e){
            responseMap.setSuccess(false);
            responseMap.setMessage("Something wrong. Please contact your administrator.");
        }

        return responseMap;
    }
}
