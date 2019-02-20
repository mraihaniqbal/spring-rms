package com.mraihaniqbal.bootcamp.springrms.entity;

import com.mraihaniqbal.bootcamp.springrms.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> user;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public UserRoleEnum getAuthority() {
        return authority;
    }

    public void setAuthority(UserRoleEnum authority) {
        this.authority = authority;
    }
}
