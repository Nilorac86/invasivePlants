package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.UserReward;
import com.carolin.invasiveplants.Entity.UserRewardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, UserRewardId> {

}
