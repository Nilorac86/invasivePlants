package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ResponseDTO.UserProfileDashboardResponseDto;
import com.carolin.invasiveplants.Service.UserService2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController2 {

    private final UserService2 userService;

    public UserController2(UserService2 userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<UserProfileDashboardResponseDto> getUserProfileDashboard(
            @AuthenticationPrincipal User user){

        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        // pass userId and points seperatly
        UserProfileDashboardResponseDto dto = userService.getUserProfileDashboard(user.getUserId(),3);

        // if service did not fetch points set them hear;
        dto.setPoints(user.getPoints());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/history")
    public ResponseEntity<UserProfileDashboardResponseDto> getUserHistory(
            @AuthenticationPrincipal User user){

        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        //PreviewSize = 0 trigger full list in service
        UserProfileDashboardResponseDto dto = userService.getUserProfileDashboard(user.getUserId(),0);

        dto.setPoints(user.getPoints());
        return ResponseEntity.ok(dto);
    }


}
