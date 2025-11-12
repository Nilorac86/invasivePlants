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

                .requiresChannel(channel -> channel
                        .anyRequest() // Every request i requierd to use https. Needed to add this to make it work.
                )

                .cors(cors -> {
                }) // enable default CORS configuration from the bean below
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No JSESSIONID is created, all authentication via JWT in every request

                .authorizeHttpRequests(auth -> auth

                        //Open endpoints
                        //remember to also add them in WebMvcConfig
                        .requestMatchers(HttpMethod.GET, "/plants/info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reportedplants/listallreportedplants").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reportedplants/listremovedplants").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()

                        //Protected endpoints
                        .requestMatchers(HttpMethod.GET, "/auth/profile").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/reported-plant/remove-form").authenticated()
                        .requestMatchers("/reportedplants/form").hasRole("USER")
                        .requestMatchers("/notifications").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/notifications/**").hasRole("USER")
                        .requestMatchers("/admin/verify").hasRole("ADMIN")

                        // everything else
                        .anyRequest().permitAll())

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
}