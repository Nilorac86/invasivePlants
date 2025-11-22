package com.carolin.invasiveplants.ResponseDTO;

public class RewardPreviewResponseDto {

    private Long rewardId;
    private String rewardTitle;
    private Integer points;
    private Integer rewardAmount;

    public RewardPreviewResponseDto() {
    }

    public RewardPreviewResponseDto(Long rewardId, String rewardTitle, Integer points, Integer rewardAmount) {
        this.rewardId = rewardId;
        this.rewardTitle = rewardTitle;
        this.points = points;
        this.rewardAmount = rewardAmount;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }
}
