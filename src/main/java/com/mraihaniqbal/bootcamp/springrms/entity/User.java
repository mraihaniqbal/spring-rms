package com.mraihaniqbal.bootcamp.springrms.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotBlank(message = "Please insert your username")
    private String username;

    @Column(length = 100)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @NotNull
    @Valid
    private UserProfile userProfile;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authorities",
            joinColumns = {
                @JoinColumn(name = "authorities_id", nullable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(name = "user_id", nullable = false)
            })
    private List<Authorities> authorities;

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    public String getAuthoritiesString(){
        return authorities.stream().map(a -> a.getAuthority()).collect(Collectors.joining(", "));
    }
}
