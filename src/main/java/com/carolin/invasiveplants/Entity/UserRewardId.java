package com.carolin.invasiveplants.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//a building block instead of its own table
//Ai tells this is mandatory due to runtime errors may happen.
//ensures consistency when keys are compared or stored.
@Embeddable
public class UserRewardId implements Serializable {

    private Long userId;
    private Long rewardId;

    // Constructors
    public UserRewardId() {
    }

    // Getters and Setters
    public UserRewardId(Long userId, Long rewardId) {
        this.userId = userId;
        this.rewardId = rewardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    // equals() and hashCode()
    //Made by ai, needed to keep the ids in a persistence context (similare to cache).
    //The methods decide if two objects represent the same database row. If its already cached, How to handle updates or merges.
    //If we don't have the methods, we can get duplicates or "detached entity passed to persist" erros. Hibranate also confuse the rows.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRewardId)) return false;
        UserRewardId that = (UserRewardId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(rewardId, that.rewardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, rewardId);
    }
}
