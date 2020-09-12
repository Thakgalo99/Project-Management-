package com.projectmanagement.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        //This Just For UserController And One Client
        //registry.addMapping("/users").
        //allowedMethods("Get","POST","PUT").
        //allowedOrigins("http://localhost:4200");

        //This For Controllers And All Clients
        registry.addMapping("/**").
                allowedMethods("*").
                allowedOrigins("*");
    }
}