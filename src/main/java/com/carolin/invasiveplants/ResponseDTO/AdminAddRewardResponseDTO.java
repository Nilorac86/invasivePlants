package com.carolin.invasiveplants.ResponseDTO;

public class AdminAddRewardResponseDTO {

    private String rewardTitle;
    private String description;
    private Integer points;
    private Integer rewardAmount;

    public AdminAddRewardResponseDTO() {
    }

    public AdminAddRewardResponseDTO(String rewardTitle, String description, Integer points, Integer rewardAmount) {
        this.rewardTitle = rewardTitle;
        this.description = description;
        this.points = points;
        this.rewardAmount = rewardAmount;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
