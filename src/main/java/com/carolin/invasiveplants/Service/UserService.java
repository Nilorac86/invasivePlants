package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.UserRegisterMapper;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.RequestDTO.UserRegisterRequestDto;
import com.carolin.invasiveplants.ResponseDTO.UserRegisterResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;

    public UserService(UserRepository userRepository, UserRegisterMapper userRegisterMapper) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
    }


    //#################################### REGISTER A USER ##################################################

    public UserRegisterResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto) {

            // Check if email already exist.
        if (userRepository.findByEmail(userRegisterRequestDto.getEmail()).isPresent()) {
            throw new ApiException("E-mail finns redan", HttpStatus.CONFLICT);
        }

            // Map to entity and then save in user.
            User user = userRegisterMapper.toEntity(userRegisterRequestDto);

            User savedUser = userRepository.save(user);

            // Converts entity to responseDto
            UserRegisterResponseDto responseDto = userRegisterMapper.toDto(savedUser);

            return responseDto;

    }

}
