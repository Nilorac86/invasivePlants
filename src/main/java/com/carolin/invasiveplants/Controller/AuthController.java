package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.JwtAuth.JwtUtil;
import com.carolin.invasiveplants.RequestDTO.LoginRequestDTO;
import com.carolin.invasiveplants.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // Making class to RestAPI controller (returning JSON)
@RequestMapping("/auth") // Every endpoint in class starts with /auth
@CrossOrigin(origins = "http://localhost:3000") // Able to request from React frontend
public class AuthController {

//    private final JwtUtil jwtUtil;
//    private final LoginRequestDTO loginRequestDTO;

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Commented out for testing
    //    public AuthController(JwtUtil jwtUtil, LoginRequestDTO loginRequestDTO) {
//        this.jwtUtil = jwtUtil;
//        this.loginRequestDTO = loginRequestDTO;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        // Fetches service with user input
        Map<String, String> tokens = authService.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );
        return ResponseEntity.ok(tokens);
    }

}

