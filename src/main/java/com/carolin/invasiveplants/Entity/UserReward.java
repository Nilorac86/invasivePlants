package com.carolin.invasiveplants.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_rewards")
public class UserReward {

    @EmbeddedId
    private UserRewardId id;

    @ManyToOne
    @MapsId("userId")  // maps userId part of the embedded key
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("rewardId")  // maps rewardId part of the embedded key
    @JoinColumn(name = "reward_id")
    private Reward reward;

    //@Column(name = "awarded_at") // If we want to implement
    //private LocalDateTime awardedAt;

    // Constructors
    public UserReward() {
    }

    public UserReward(User user, Reward reward) {
        this.user = user;
        this.reward = reward;
        this.id = new UserRewardId(user.getUserId(), reward.getRewardId());
        //this.awardedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public UserRewardId getId() {
        return id;
    }

    public void setId(UserRewardId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    /*public LocalDateTime getAwardedAt() {
        return awardedAt;
    }

    public void setAwardedAt(LocalDateTime awardedAt) {
        this.awardedAt = awardedAt;
    }*/
}
