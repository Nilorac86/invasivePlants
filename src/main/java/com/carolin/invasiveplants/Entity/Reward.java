package com.carolin.invasiveplants.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private Long rewardId;

    private Integer points;

    @Column(name = "reward-depending-on-points", columnDefinition = "int")
    private Integer rewardDependingOn;

    @Column(columnDefinition = "mediumtext")
    private String description;

    // Constructors
    public Reward() {
    }

    public Reward(Integer points, Integer rewardDependingOn, String description) {
        this.points = points;
        this.rewardDependingOn = rewardDependingOn;
        this.description = description;
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

    public Integer getRewardDependingOn() {
        return rewardDependingOn;
    }

    public void setRewardDependingOn(Integer rewardDependingOn) {
        this.rewardDependingOn = rewardDependingOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}