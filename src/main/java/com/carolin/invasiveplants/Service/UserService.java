package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.UserRegisterMapper;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.RequestDTO.UserRegisterRequestDto;
import com.carolin.invasiveplants.ResponseDTO.UserRegisterResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRegisterMapper userRegisterMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordEncoder = passwordEncoder;
    }


    //#################################### REGISTER A USER ##################################################

    public UserRegisterResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto) {

            // Check if email already exist.
        if (userRepository.findByEmail(userRegisterRequestDto.getEmail()).isPresent()) {
            throw new ApiException("E-mail finns redan", HttpStatus.CONFLICT);
        }


            // Map to entity and then save in user.
            User user = userRegisterMapper.toEntity(userRegisterRequestDto);
            user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
            User savedUser = userRepository.save(user);

            // Converts entity to responseDto
            UserRegisterResponseDto responseDto = userRegisterMapper.toDto(savedUser);

            return responseDto;

    }

}
