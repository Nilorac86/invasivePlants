package com.carolin.invasiveplants.RequestDTO;

import jakarta.validation.constraints.*;

public class AdminAddRewardRequestDTO {

    private Long rewardId;

    @NotNull(message = "Du måste ange hur många poäng vinsten är värd.")
    @Min(value = 1, message = "Poängen måste vara minst 1.")
    @Max(value= 1000, message= "Poängen får vara högst 1000.")
    private Integer points;

    @NotBlank(message = "Du måste fylla i en titel")
    @Size( max = 20, message = "Titeln får vara max 20 tecken lång.")
    private String rewardTitle;

    @NotBlank(message = "Du måste fylla i en beskrivelse")
    @Size( max = 100, message = "Beskrivningen får vara max 100 tecken lång.")
    private String description;

    @NotNull(message = "Du måste ange hur många belöningar som finns.")
    @Min(value=0, message ="Analet belöningar kan inte vara negativt.")
    private Integer rewardAmount;

    public AdminAddRewardRequestDTO() {
    }

    public AdminAddRewardRequestDTO(Long rewardId, Integer points, String rewardTitle, String description, Integer rewardAmount) {
        this.rewardId = rewardId;
        this.points = points;
        this.rewardTitle = rewardTitle;
        this.description = description;
        this.rewardAmount = rewardAmount;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }
}
