package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.UserReward;
import com.carolin.invasiveplants.ResponseDTO.RewardPreviewResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardPreviewMapper {

    public RewardPreviewResponseDto toDto (UserReward userReward){

        return new RewardPreviewResponseDto(
        userReward.getId().getRewardId(),
        userReward.getReward().getRewardTitle(),
        userReward.getReward().getPoints(),
        userReward.getReward().getRewardAmount());
    }
}
