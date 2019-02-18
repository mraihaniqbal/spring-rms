package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.UserProfileDao;
import com.mraihaniqbal.bootcamp.springrms.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private UserProfileDao userProfileDao;

    @Autowired
    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    public UserProfile findByUsername(String username){
        return userProfileDao.findByUsername(username);
    }
}
