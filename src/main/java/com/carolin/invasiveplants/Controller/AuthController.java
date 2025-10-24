package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.RequestDTO.LoginRequestDTO;
import com.carolin.invasiveplants.Service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // Making class to RestAPI controller (returning JSON)
@RequestMapping("/auth") // Every endpoint in class starts with /auth
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true") // Able to request from React frontend
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

    @PostMapping("/login") //@Valid needed for GlobalExceptionHandler
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {

        //  Delegates authentication to the service layer
        Map<String, String> tokens = authService.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );


        //Create HttpOnly cookie for JWT (inaccessible from JavaScript, prevents XSS attacks)
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", tokens.get("accessToken"))
         .httpOnly(true)
                .secure(false)  // true for HTTPS in production
                .path("/")
                .maxAge(3600)  // 1 timme
                .sameSite("None")
                .build(); // 1 hour


        // Adds cookie to the response header
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of(
                        "message", "Login successful",
                        "email", tokens.get("email"),
                        "role", tokens.get("role")

        ));

    }

    /**
     * Handles user logout.
     * Deletes the JWT cookie by setting Max-Age = 0.
     * Service method (logout) can later blacklist tokens if needed.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue(name = "jwt", required = false) String token,
            HttpServletResponse response) {

        // Logout via service
        authService.logout(token);

        // Remove cookie by setting maxAge to 0 and set empty value
        ResponseCookie deleteCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());

        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    /**
     * Returns info about the currently logged-in user - right now just email and role.
     * Throws ApiException(UNAUTHORIZED) if token is missing or invalid.
     *
     * 🔹 In the future, this endpoint can serve as a "Profile Page" backend endpoint,
     *     if expanded with user details like name, role, points, and rating.
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser(
            @CookieValue(name = "jwt", required = false) String token) {

        if (token == null) {
            // GlobalExceptionHandler catches this and returns 401 JSON error response
            throw new ApiException("Not authenticated", HttpStatus.UNAUTHORIZED);
        }

        // Fetch user info from token and database (in service layer)
        Map<String, String> userInfo = authService.getUserFromToken(token);
        return ResponseEntity.ok(userInfo);

    }

}

