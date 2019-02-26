package com.mraihaniqbal.bootcamp.springrms.dao;

import com.mraihaniqbal.bootcamp.springrms.entity.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao extends CrudRepository<UserProfile,Long> {

    @Query("from UserProfile where user.username = ?1")
    UserProfile findByUsername(String username);

}
