package com.mraihaniqbal.bootcamp.springrms.entity;

import com.mraihaniqbal.bootcamp.springrms.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,name = "user_name")
    private String username;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_project",
            joinColumns = {
                @JoinColumn(name = "project_id", nullable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(name = "user_id", nullable = false)
            })
    private List<Project> projects;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
