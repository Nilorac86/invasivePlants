package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.JwtAuth.JwtUtil;
import com.carolin.invasiveplants.JwtAuth.TokenBlacklistService;
import com.carolin.invasiveplants.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }


    // Method to generate token * Authenticates user by email and password.
    //Throws ApiException with appropriate status if user not found or password invalid.
    public Map<String, String> login (String email,String password) {

        // Fetches users from email or throw 404 if not found
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));


        // Controlls if password matches with the users stored password otherwise 401 if incorrect.
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }


        // Generates a JWT Token for the user.
        String accessToken = jwtUtil.generateToken(user);


        // Return token and basic user info
        return Map.of(
                "accessToken", accessToken,
                "email", user.getEmail(),
                "role", user.getRole().getRoleName()
        );
    }



     // Retrieves user info from valid JWT token. Throws ApiException if token is invalid, expired, or user not found.
    public Map<String, String> getUserFromToken(String token) {

        //Validate token first
        if (!jwtUtil.validateToken(token)) {
            throw new ApiException("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }

        //Get email from token
        String email = jwtUtil.getUsernameFromToken(token);

        //Fetch user from database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        //return user info
        return Map.of(
                "email", user.getEmail(),
                "role", user.getRole().getRoleName()

        );
    }
    // Logout with tokenblacklist
    public void logout(String token) {
        if (token == null || token.isEmpty()) {
            throw new ApiException("No token provided for logout", HttpStatus.BAD_REQUEST);
        }
        //get token expiration time from jwt
        long expirationTime = jwtUtil.getExpirationTimeFromToken(token);

        //Add token to blacklist until it expires
        tokenBlacklistService.addBlacklist(token, expirationTime);
    }

}
