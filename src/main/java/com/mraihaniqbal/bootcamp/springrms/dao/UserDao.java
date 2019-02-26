package com.mraihaniqbal.bootcamp.springrms.dao;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
