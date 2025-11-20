package com.carolin.invasiveplants.RequestDTO;

public class PickRewardRequestDto {

    private Long rewardId;

    public PickRewardRequestDto() {
    }

    public PickRewardRequestDto(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }
}
