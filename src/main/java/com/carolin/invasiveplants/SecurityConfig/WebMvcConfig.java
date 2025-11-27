package com.carolin.invasiveplants.SecurityConfig;

import com.carolin.invasiveplants.JwtAuth.JwtAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebMvcConfig(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }


    //Allows frontend to send cookies and authenticated requests
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Set-Cookie");
    }


    // It´s a filter that every path goes through with every request except exclude patterns.
    // Every public path needs to be put in the excludePathPatterns.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/**") // Checks every path that should be controlled
                .excludePathPatterns( // Every path that should not be controlled, public ones.
                        "/auth/login",
                        "/auth/logout",
                        "/plants/info",
                        "/remove-plant/list",
                        "/error",
                        "/report-plant/list",
                        "/plants/{id}",
                        "/users/register"
                );
    }

}
