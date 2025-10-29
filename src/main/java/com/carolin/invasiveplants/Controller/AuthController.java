package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.RequestDTO.LoginRequestDTO;
import com.carolin.invasiveplants.SecurityConfig.CookieUtil;
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

    private final AuthService authService;
    private final CookieUtil cookieUtil;

    public AuthController(AuthService authService, CookieUtil cookieUtil) {

        this.authService = authService;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping("/login") //@Valid needed for GlobalExceptionHandler
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {

        //  Delegates authentication to the service layer
        Map<String, String> tokens = authService.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );

        // Create JWT cookie using the utility
        ResponseCookie jwtCookie = cookieUtil.createJwtCookie(tokens.get("accessToken"));

        // Adds cookie to the response header
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of(
                        "message", "Login successful",
                        "email", tokens.get("email"),
                        "role", tokens.get("role")
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            //fetch cookie named "jwt" and put value in the token variable. This is then used in JwtUtil for validation and get userinfo.
            //@CookieValue is needed for protected endpoints (logged in user).
            @CookieValue(name = "jwt", required = false) String token,
            HttpServletResponse response) {

        // Logout via service
        authService.logout(token);

        // Delete JWT cookie using the utility
        ResponseCookie deleteCookie = cookieUtil.deleteJwtCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(Map.of("message", "Logged out successfully"));
    }

    /**
     * Returns info about the currently logged-in user - right now just email and role.
     * Throws ApiException(UNAUTHORIZED) if token is missing or invalid.
     *  In the future, this endpoint can serve as a "Profile Page" backend endpoint,
     *     if expanded with user details like name, role, points, and rating.
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser(
            //Spring reads cookie named "jwt" automatically and puts value in the token variable
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

