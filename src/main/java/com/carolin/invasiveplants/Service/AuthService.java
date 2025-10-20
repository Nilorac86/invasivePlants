package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.JwtAuth.JwtUtil;
import com.carolin.invasiveplants.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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


    // Metod för login som genererar token.
    public Map<String, String> login (String email,String password) {

        // Hämtar användare baserat på email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kontrollerar om lösenordet stämmer med användarens lagrade om inte får användaren ett felmeddelande.
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("\"Invalid credentials\"");
        }

        // Genererar en ny JWT token för den användaren.
        String accessToken = jwtUtil.generateToken(user);


        // Listar den genererade token, email och användarens roll.
        return Map.of(
                "accessToken", accessToken,
                "email", user.getEmail(),
                "role", user.getRole().getRoleName()
        );
    }
}
