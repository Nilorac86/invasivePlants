package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Reward;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListRewardsMapper {

    public ListRewardResponseDTO toDto(Reward reward, int userPoints){

        if(reward == null){
            return null;
        }

        int rewardPoints = reward.getPoints() == null ? Integer.MAX_VALUE : reward.getPoints();
        boolean affordable = userPoints >= rewardPoints;

        return new ListRewardResponseDTO(
                reward.getRewardId(),
                reward.getRewardTitle(),
                reward.getDescription(),
                reward.getPoints(),
                reward.getRewardAmount(),
                affordable
        );
    }

}
