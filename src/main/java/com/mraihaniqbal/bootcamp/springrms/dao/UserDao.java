package com.mraihaniqbal.bootcamp.springrms.dao;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User,Long> {

    @Transactional
    @Query("from User where username = ?1")
    User findByUsername(String username);
}
