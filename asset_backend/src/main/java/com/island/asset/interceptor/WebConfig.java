package com.island.asset.interceptor;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private LoginIntercepter loginIntercepter;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*");

    }
}


