package com.mraihaniqbal.bootcamp.springrms.config;

import com.mraihaniqbal.bootcamp.springrms.entity.Project;
import com.mraihaniqbal.bootcamp.springrms.entity.User;
import com.mraihaniqbal.bootcamp.springrms.pojo.ResponseMap;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class RmsConfig {

    private Environment env;

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }

    @Bean
    public User user(){
        return new User();
    }

    @Bean
    public Project project(){
        return new Project();
    }

    @Bean
    public ResponseMap response(){
        return new ResponseMap(false,"");
    }

}
