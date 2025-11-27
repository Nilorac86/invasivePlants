package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ResponseDTO.UserNameResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserNameMapper {

    public UserNameResponseDto toDto(User user){

        if(user == null){
            return null;
        }

        return new UserNameResponseDto(
                user.getFirstName(),
                user.getLastName()
        );
    }
}
