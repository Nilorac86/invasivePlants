package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Reward;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Entity.UserReward;
import com.carolin.invasiveplants.Entity.UserRewardId;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ListRewardsMapper;
import com.carolin.invasiveplants.Mapper.UserPointsMapper;
import com.carolin.invasiveplants.Repository.RewardRepository;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.Repository.UserRewardRepository;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.UserPointsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;
    private final ListRewardsMapper listRewardsMapper;
    private final UserRewardRepository userRewardRepository;

    private final UserPointsMapper userPointsMapper;
    private final UserRepository userRepository;

    public RewardService(RewardRepository rewardRepository, ListRewardsMapper listRewardsMapper, UserRewardRepository userRewardRepository, UserPointsMapper userPointsMapper, UserRepository userRepository) {
        this.rewardRepository = rewardRepository;
        this.listRewardsMapper = listRewardsMapper;
        this.userRewardRepository = userRewardRepository;
        this.userPointsMapper = userPointsMapper;
        this.userRepository = userRepository;
    }

    // ##################################### LIST REWARDS #######################################

    @GetMapping("/list-rewards")
    public List<ListRewardResponseDTO> listRewads(User user){

        int userPoints = (user == null || user.getPoints() == null) ? 0 : user.getPoints();

        return rewardRepository.findAll().stream()
                .filter(r -> r.getRewardAmount() != null && r.getRewardAmount() > 0)
                .sorted(Comparator.comparing(Reward::getPoints, Comparator.nullsLast(Integer::compareTo)))
                .map(r -> listRewardsMapper.toDto(r, userPoints))
                .collect(Collectors.toList());
    }

    // ################################ PICK A REWARD #############################################

    @Transactional
    public void pickReward(User user, Long rewardId){

        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(()-> new ApiException("Reward not found",HttpStatus.NOT_FOUND));

        if(reward.getPoints() > user.getPoints()){
            throw new ApiException("Not enough points", HttpStatus.BAD_REQUEST);
        }

        if(reward.getRewardAmount()<=0){
            throw new ApiException("Reward is out of stock",HttpStatus.BAD_REQUEST);
        }

        // adjust how many rewards that are left and the users points
        user.setPoints(user.getPoints() - reward.getPoints());
        reward.setRewardAmount(reward.getRewardAmount() -1);

        userRepository.save(user);
        rewardRepository.save(reward);

        UserRewardId userRewardId = new UserRewardId(user.getUserId(),reward.getRewardId());

        UserReward userReward = userRewardRepository.findById(userRewardId)
                .orElse(new UserReward());

        if(userReward.getId()== null){
            userReward.setId(userRewardId);
            userReward.setUser(user);
            userReward.setReward(reward);
            userReward.setQuantity(1);
        }else{
            //Existing UserRewards, increment quantity
            userReward.setQuantity(userReward.getQuantity() + 1);
        }

        userRewardRepository.save(userReward);

    }

    // ################################ USERS POINTS #############################################

    public UserPointsResponseDto getUserPoints(User user){

        // If user has no points return 0 points insted of null
        int points = (user == null || user.getPoints() == null) ? 0 : user.getPoints();
        user.setPoints(points);

        return userPointsMapper.toDto(user);
    }

}
