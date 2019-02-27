package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.ProjectDao;
import com.mraihaniqbal.bootcamp.springrms.entity.Project;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectDao projectDao;
    private ResponseMap responseMap;

    @Autowired
    public ProjectService(ProjectDao projectDao, ResponseMap responseMap) {
        this.projectDao = projectDao;
        this.responseMap = responseMap;
    }

    public List<Project> getList(){
        return projectDao.findAll();
    }

    public List<Project> getList(String username){
        return projectDao.findByUser_Username(username);
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
}
