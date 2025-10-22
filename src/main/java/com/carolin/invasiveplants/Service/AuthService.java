package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.JwtAuth.JwtUtil;
import com.carolin.invasiveplants.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    // Method to generate token
    public Map<String, String> login (String email,String password) {

        // Fetches users from email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Controlls if password matches with the users stored password otherwise error.
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("\"Invalid credentials\"");
        }


        // Generates a JWT Token for the user.
        String accessToken = jwtUtil.generateToken(user);


        // A list for the generated token, email and the users roll.
        return Map.of(
                "accessToken", accessToken,
                "email", user.getEmail(),
                "role", user.getRole().getRoleName()
        );
    }
}
