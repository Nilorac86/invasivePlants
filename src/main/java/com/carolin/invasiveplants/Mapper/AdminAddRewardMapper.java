package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Reward;
import com.carolin.invasiveplants.RequestDTO.AdminAddRewardRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminAddRewardResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AdminAddRewardMapper {

    public AdminAddRewardResponseDTO responseDTO(Reward reward){

        if(reward == null){
            return null;
        }

        return new AdminAddRewardResponseDTO(
                reward.getRewardTitle(),
                reward.getDescription(),
                reward.getPoints(),
                reward.getRewardAmount()
        );
    }

    public Reward mapToEntity(AdminAddRewardRequestDTO dto){

        if(dto == null){
            return null;
        }

        Reward reward = new Reward();
        reward.setRewardTitle(dto.getRewardTitle());
        reward.setDescription(dto.getDescription());
        reward.setPoints(dto.getPoints());
        reward.setRewardAmount(dto.getRewardAmount());
        return reward;
    }

}
