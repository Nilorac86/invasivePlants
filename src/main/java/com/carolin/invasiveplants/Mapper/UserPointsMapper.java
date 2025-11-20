package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ResponseDTO.UserPointsResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserPointsMapper {

    public UserPointsResponseDto toDto(User user){

        if (user == null) {
            return null;
        }

        return new UserPointsResponseDto(
                user.getPoints()
        );
    }

}
