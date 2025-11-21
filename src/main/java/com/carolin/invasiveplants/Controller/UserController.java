package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Mapper.UserRegisterMapper;
import com.carolin.invasiveplants.RequestDTO.UserRegisterRequestDto;
import com.carolin.invasiveplants.ResponseDTO.UserRegisterResponseDto;
import com.carolin.invasiveplants.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
