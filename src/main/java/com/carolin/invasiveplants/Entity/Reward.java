package com.carolin.invasiveplants.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private Long rewardId;

    @Column(name = "points")
    private Integer points;

    @Column(name = "reward_title")
    private String rewardTitle;

    @Column(columnDefinition = "mediumtext")
    private String description;

    @Column(name = "reward_amount")
    private Integer rewardAmount;

    // Constructors

    public Reward() {
    }

    public Reward(Long rewardId, Integer points, String rewardTitle, String description, Integer rewardAmount) {
        this.rewardId = rewardId;
        this.points = points;
        this.rewardTitle = rewardTitle;
        this.description = description;
        this.rewardAmount = rewardAmount;
    }

// Getters and Setters


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