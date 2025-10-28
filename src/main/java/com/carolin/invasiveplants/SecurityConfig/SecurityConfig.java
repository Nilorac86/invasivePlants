package com.carolin.invasiveplants.SecurityConfig;

import com.carolin.invasiveplants.JwtAuth.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http


                .cors(cors -> {}) // enable default CORS configuration from the bean above
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No JSESSIONID is created, all authentication via JWT in every request

                .authorizeHttpRequests(auth -> auth

                        //Open endpoints
                        .requestMatchers(HttpMethod.GET, "/plants/info").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/profile").authenticated()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").authenticated()

                        //Closed endpoints
                        .requestMatchers("/reportedPlants/form").hasRole("USER"))

                // Adds jwt token before standard loginfilter runs.
                // For control of expiration token before every request.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    // Without hashed password. Method needed to add JWT in Service
    @Bean
    public PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    //Allows frontend to send cookies and authenticated requests
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") //what domain can communicate with the server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //what HTTP method frontend can use
                        .allowedHeaders("*")
                        .allowCredentials(true); // needed to send cookies
            }
        };
    }
}
