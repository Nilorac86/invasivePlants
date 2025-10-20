package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.JwtAuth.JwtUtil;
import com.carolin.invasiveplants.RequestDTO.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController  // <- Gör klassen till en REST API controller (returnerar JSON automatiskt)
@RequestMapping("/auth") // <- Alla endpoints i denna klass börjar med /auth
@CrossOrigin(origins = "http://localhost:3000") // <- Tillåt requests från React frontend
public class AuthController {

    private final JwtUtil jwtUtil;
    private final LoginRequestDTO loginRequestDTO;

    public AuthController(JwtUtil jwtUtil, LoginRequestDTO loginRequestDTO) {
        this.jwtUtil = jwtUtil;
        this.loginRequestDTO = loginRequestDTO;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO){

        // anropar service med användarens input
        //inte färdig!!!!!! (se caroline sida)
        Map<String, String> tokens = service.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );
        return ResponseEntity.ok(tokens);
    }

    }
}
