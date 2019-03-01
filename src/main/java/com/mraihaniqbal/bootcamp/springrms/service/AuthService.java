package com.mraihaniqbal.bootcamp.springrms.service;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    private UserService userService;
    private ResponseMap responseMap;

    @Autowired
    public AuthService(UserService userService, ResponseMap responseMap) {
        this.userService = userService;
        this.responseMap = responseMap;
    }

    public ResponseMap login(String username, String password, HttpSession session){
        User user = userService.findByUsername(username);

        if(user != null){
            if(user.getPassword().equals(password)){
                // do some session

                Object userId = session.getAttribute("userId");

                if(userId == null){
                    userId = user.getId();
                    session.setAttribute("userId",userId);
                }

                responseMap.setSuccess(true);
                responseMap.setMessage("Login success!");

                return responseMap;
            }

            responseMap.setMessage("Wrong Password!");
            return responseMap;
        }

        responseMap.setMessage("Username not found!");
        return responseMap;
    }

    public ResponseMap logout(HttpSession session, Authentication auth, HttpServletRequest req, HttpServletResponse res){
        session.invalidate();

        if(auth != null){
            new SecurityContextLogoutHandler().logout(req, res, auth);
        }

        responseMap.setSuccess(true);
        responseMap.setMessage("You have been logged out");

        return responseMap;
    }
}
