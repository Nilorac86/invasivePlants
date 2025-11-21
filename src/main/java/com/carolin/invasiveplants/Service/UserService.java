package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.Entity.UserReward;
import com.carolin.invasiveplants.Enum.RemovePlantStatus;
import com.carolin.invasiveplants.Mapper.UserRemovedPlantsStatusMapper;
import com.carolin.invasiveplants.Repository.RemovePlantRepository;
import com.carolin.invasiveplants.Repository.UserRewardRepository;
import com.carolin.invasiveplants.ResponseDTO.RewardPreviewResponseDto;
import com.carolin.invasiveplants.ResponseDTO.UserProfileDashboardResponseDto;
import com.carolin.invasiveplants.ResponseDTO.UserRemovedPlantsStatusResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final RemovePlantRepository removePlantRepository;
    private final UserRewardRepository userRewardRepository;
    private final UserRemovedPlantsStatusMapper userRemovedPlantsStatusMapper;


    public UserService(RemovePlantRepository removePlantRepository, UserRewardRepository userRewardRepository, UserRemovedPlantsStatusMapper userRemovedPlantsStatusMapper) {
        this.removePlantRepository = removePlantRepository;
        this.userRewardRepository = userRewardRepository;
        this.userRemovedPlantsStatusMapper = userRemovedPlantsStatusMapper;
    }

    public UserProfileDashboardResponseDto getUserProfileDashboard(Long userId, Integer previewSize){

        if (previewSize == null || previewSize <=0) previewSize = 3;

        // Points - load user entity if needed elsewhere; here I'll assume controller passes user points or you load user separately
        // If you prefer to fetch user here, inject UserRepository and load user.getPoints()
        Integer points = null;

        // Total for pending and approved
        long pendingTotal = removePlantRepository.countByStatusAndRemovedBy_UserId(RemovePlantStatus.PENDING, userId);
        long appovedTotal = removePlantRepository.countByStatusAndRemovedBy_UserId(RemovePlantStatus.APPROVED, userId);

        // Preview (newest first) using pageable
        PageRequest previewPage = PageRequest.of(0, previewSize);

        List<RemovedPlant> pendingList = removePlantRepository.findByStatusAndRemovedBy_UserId(RemovePlantStatus.PENDING, userId, previewPage);
        List<RemovedPlant> approvedList = removePlantRepository.findByStatusAndRemovedBy_UserId(RemovePlantStatus.APPROVED, userId, previewPage);

        // Map to DTO previews
        List<UserRemovedPlantsStatusResponseDto> penndingPreview = userRemovedPlantsStatusMapper.toDto(pendingList);
        List<UserRemovedPlantsStatusResponseDto> approvedPreview = userRemovedPlantsStatusMapper.toDto(approvedList);

        // Gifts / rewards
        long giftsTotal = userRewardRepository.countByUser_UserId(userId);

        List<UserReward> rewardEntities = userRewardRepository.findByUser_UserIdOrderByReward_RewardIdDesc(userId, previewPage);
        List<RewardPreviewResponseDto> giftPreview = rewardEntities.stream()
                .map(ur ->{
                    RewardPreviewResponseDto dto = new RewardPreviewResponseDto();
                    dto.setRewardId(ur.getReward().getRewardId());
                    dto.setRewardTitle(ur.getReward().getRewardTitle());
                    dto.setPoints(ur.getReward().getPoints());
                    dto.setRewardAmount(ur.getReward().getRewardAmount());
                    return dto;
                }).collect(Collectors.toList());

        // building up the frontend DTO
        UserProfileDashboardResponseDto dto = new UserProfileDashboardResponseDto();
        dto.setPoints(points);// set after you load user
        dto.setPendingTotal(pendingTotal);
        dto.setApprovedTotal(appovedTotal);
        dto.setGiftsTotal(giftsTotal);
        dto.setPendingPreview(penndingPreview);
        dto.setApprovedPreview(approvedPreview);
        dto.setGiftsPreview(giftPreview);
        return dto;

    }
}
