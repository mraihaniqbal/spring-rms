package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.UserDao;
import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao;
    private ResponseMap responseMap;

    @Autowired
    public UserService(UserDao userDao, ResponseMap responseMap) {
        this.userDao = userDao;
        this.responseMap = responseMap;
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

    public ResponseMap add(User user){

        try{
            userDao.save(user);
            responseMap.setSuccess(true);
            responseMap.setMessage("New user successfully added");

        }catch (DataAccessException d){

            responseMap.setSuccess(false);
            responseMap.setMessage("Failed to add new user. Contact your administrator.");

            logger.error(d.getMessage());
            logger.error(Arrays.toString(d.getStackTrace()));
        }

        return responseMap;
    }
}
