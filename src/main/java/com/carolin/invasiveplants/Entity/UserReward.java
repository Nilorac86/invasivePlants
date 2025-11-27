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

    @Column(name = "quantity")
    private Integer quantity = 0;

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

    public UserReward(UserRewardId id, Integer quantity, User user, Reward reward) {
        this.id = id;
        this.quantity = quantity;
        this.user = user;
        this.reward = reward;
    }

    // Getters and Setters

    public UserRewardId getId() {
        return id;
    }

    public void setId(UserRewardId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
