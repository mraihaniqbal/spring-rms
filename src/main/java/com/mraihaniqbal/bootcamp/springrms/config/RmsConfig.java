package com.mraihaniqbal.bootcamp.springrms.config;

import com.mraihaniqbal.bootcamp.springrms.entity.User;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RmsConfig {

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }

    @Bean
    public User user(){
        return new User();
    }

}
