package com.carolin.invasiveplants.ResponseDTO;

public class ListRewardResponseDTO {

    private String rewardTitle;
    private String description;
    private Integer points;
    private Integer rewardAmount;
    private boolean affordable;

    public ListRewardResponseDTO() {
    }

    public ListRewardResponseDTO(String rewardTitle, String description, Integer points, Integer rewardAmount, boolean affordable) {
        this.rewardTitle = rewardTitle;
        this.description = description;
        this.points = points;
        this.rewardAmount = rewardAmount;
        this.affordable = affordable;
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

    public boolean isAffordable() {
        return affordable;
    }

    public void setAffordable(boolean affordable) {
        this.affordable = affordable;
    }
}
