package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.dao.UserProfileDao;
import com.mraihaniqbal.bootcamp.springrms.entity.UserProfile;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private UserProfileDao userProfileDao;
    private ResponseMap responseMap;

    @Autowired
    public UserProfileService(UserProfileDao userProfileDao, ResponseMap responseMap) {
        this.userProfileDao = userProfileDao;
        this.responseMap = responseMap;
    }

    public UserProfile findByUsername(String username){
        return userProfileDao.findByUsername(username);
    }

    public ResponseMap edit(UserProfile userProfile){
        try{
            userProfileDao.save(userProfile);
            responseMap.setSuccess(true);
            responseMap.setMessage("Edit Profile Success!");
        }catch (DataAccessException e){
            e.printStackTrace();
            responseMap.setMessage("Something wrong. Please contact your administrator");
            responseMap.setSuccess(false);
        }

        return responseMap;
    }
}
