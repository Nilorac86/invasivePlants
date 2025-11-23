package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Entity.UserReward;
import com.carolin.invasiveplants.Entity.UserRewardId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, UserRewardId> {

    // count rewards for user
    long countByUser_UserId(Long userId);

    // Previw rewards in order and how many per page
    List<UserReward>findByUser_UserIdOrderByReward_RewardIdDesc(Long userId, Pageable pageable);

    List<UserReward>findByUser_UserIdOrderByReward_RewardIdDesc(Long userId, Sort sort);

}
