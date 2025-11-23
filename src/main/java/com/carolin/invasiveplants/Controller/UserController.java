package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.UserRegisterMapper;
import com.carolin.invasiveplants.RequestDTO.UserRegisterRequestDto;
import com.carolin.invasiveplants.ResponseDTO.UserNameResponseDto;
import com.carolin.invasiveplants.ResponseDTO.UserProfileDashboardResponseDto;
import com.carolin.invasiveplants.ResponseDTO.UserRegisterResponseDto;
import com.carolin.invasiveplants.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    // ################################## REGISTER A USER #################################################

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@Valid @RequestBody
                                                                UserRegisterRequestDto userRegisterRequestDto){

        UserRegisterResponseDto responseDto = userService.createUser(userRegisterRequestDto);

        return new ResponseEntity<> (responseDto, HttpStatus.CREATED);
    }

    // ################################## DASHBOARD USER #################################################

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

    // ################################## HISTORY USER #################################################

    @GetMapping("/history")
    public ResponseEntity<UserProfileDashboardResponseDto> getUserHistory(
            @AuthenticationPrincipal User user){

        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        //PreviewSize = 0 trigger full list in service
        UserProfileDashboardResponseDto dto = userService.getUserProfileDashboard(user.getUserId(),0);

        dto.setPoints(user.getPoints());
        return ResponseEntity.ok(dto);
    }

    // ################################## GET USER NAME #################################################

    @GetMapping("/name")
    public ResponseEntity<UserNameResponseDto> getName(@AuthenticationPrincipal User user){

        if(user == null){
            throw new ApiException("User not authenticated", HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(userService.getUsername(user.getUserId()));

    }

}
