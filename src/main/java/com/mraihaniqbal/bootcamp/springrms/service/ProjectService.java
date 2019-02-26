package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.ProjectDao;
import com.mraihaniqbal.bootcamp.springrms.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectDao projectDao;

    @Autowired
    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public List<Project> getList(){
        return projectDao.findAll();
    }

    public List<Project> getList(String username){
        return projectDao.findByUser_Username(username);
    }
}
