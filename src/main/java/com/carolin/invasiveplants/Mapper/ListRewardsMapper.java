package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Reward;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListRewardsMapper {

    public ListRewardResponseDTO toDto(Reward reward){

        if(reward == null){
            return null;
        }

        return new ListRewardResponseDTO(
                reward.getRewardTitle(),
                reward.getDescription(),
                reward.getPoints(),
                reward.getRewardAmount()
        );
    }

    public List<ListRewardResponseDTO> toDto(List<Reward>rewardList){

        if(rewardList== null){
            return Collections.emptyList();
        }

        return rewardList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

}
