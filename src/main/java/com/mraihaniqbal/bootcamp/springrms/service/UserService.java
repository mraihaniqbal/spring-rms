package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.UserDao;
import com.mraihaniqbal.bootcamp.springrms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(Long id){
        Optional<User> data = userDao.findById(id);
        return data.orElse(null);
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
