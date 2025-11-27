package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Entity.UserReward;
import com.carolin.invasiveplants.Enum.RemovePlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.*;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.RemovePlantRepository;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.Repository.UserRewardRepository;
import com.carolin.invasiveplants.RequestDTO.UserRegisterRequestDto;
import com.carolin.invasiveplants.ResponseDTO.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordEncoder passwordEncoder;

    private final RemovePlantRepository removePlantRepository;
    private final UserRewardRepository userRewardRepository;
    private final UserRemovedPlantsStatusMapper userRemovedPlantsStatusMapper;
    private final RewardPreviewMapper rewardPreviewMapper;
    private final ReportedPreviewMapper reportedPreviewMapper;
    private final PlantRepository plantRepository;

    private final UserNameMapper userNameMapper;

    public UserService(UserRepository userRepository, UserRegisterMapper userRegisterMapper, RemovePlantRepository removePlantRepository, UserRewardRepository userRewardRepository, UserRemovedPlantsStatusMapper userRemovedPlantsStatusMapper, RewardPreviewMapper rewardPreviewMapper, ReportedPreviewMapper reportedPreviewMapper, PlantRepository plantRepository, UserNameMapper userNameMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.removePlantRepository = removePlantRepository;
        this.userRewardRepository = userRewardRepository;
        this.userRemovedPlantsStatusMapper = userRemovedPlantsStatusMapper;
        this.rewardPreviewMapper = rewardPreviewMapper;
        this.reportedPreviewMapper = reportedPreviewMapper;
        this.plantRepository = plantRepository;
        this.userNameMapper = userNameMapper;
        this.passwordEncoder = passwordEncoder;
        
    }


    //#################################### REGISTER A USER ##################################################

    public UserRegisterResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto) {

            // Check if email already exist.
        if (userRepository.findByEmail(userRegisterRequestDto.getEmail()).isPresent()) {
            throw new ApiException("E-mail finns redan", HttpStatus.CONFLICT);
        }


            // Map to entity and then save in user.
            User user = userRegisterMapper.toEntity(userRegisterRequestDto);
            user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
            User savedUser = userRepository.save(user);

            // Converts entity to responseDto
            UserRegisterResponseDto responseDto = userRegisterMapper.toDto(savedUser);

            return responseDto;

    }

    //#################################### USER DASHBOARD ##################################################

    public UserProfileDashboardResponseDto getUserProfileDashboard(Long userId, Integer previewSize){

        // If previewSize is null or <=0, fetch full list (no paging)
        boolean fetchAll = (previewSize == null || previewSize <= 0);

        // Points - you can load user points here or pass them in
        Integer points = null;

        //Totals
        long pendingTotal = removePlantRepository.countByStatusAndRemovedBy_UserId(RemovePlantStatus.PENDING, userId);
        long approvedTotal = removePlantRepository.countByStatusAndRemovedBy_UserId(RemovePlantStatus.APPROVED, userId);
        long giftsTotal = userRewardRepository.countByUser_UserId(userId);
        long reportedTotal = plantRepository.countByReportedBy_UserId(userId);

        List<RemovedPlant> pendingList;
        List<RemovedPlant>aprovedList;
        List<UserReward>rewardEntities;
        List<Plant>reportedEntities;

        if(fetchAll){

            Sort sortByRemoved = Sort.by(Sort.Direction.DESC,"removedAt");
            Sort sortByReward = Sort.by(Sort.Direction.DESC, "reward.rewardId");
            Sort sortByPlant = Sort.by(Sort.Direction.DESC, "plantId");

            pendingList = removePlantRepository.findByStatusAndRemovedBy_UserId(
                    RemovePlantStatus.PENDING, userId, sortByRemoved);

            aprovedList = removePlantRepository.findByStatusAndRemovedBy_UserId(
                    RemovePlantStatus.APPROVED, userId, sortByRemoved);

            rewardEntities = userRewardRepository.findByUser_UserIdOrderByReward_RewardIdDesc(
                    userId, sortByReward);

            reportedEntities = plantRepository.findByReportedBy_UserIdOrderByPlantIdDesc(
                    userId, sortByPlant);


        }else{
            // Fetch paged list for preview
            PageRequest previewPage = PageRequest.of(0, previewSize, Sort.by(Sort.Direction.DESC,"removedAt"));
            PageRequest previewPageRewards = PageRequest.of(0, previewSize, Sort.by(Sort.Direction.DESC, "reward.rewardId"));
            PageRequest previewPageReported = PageRequest.of(0, previewSize, Sort.by(Sort.Direction.DESC,"plantId"));

            pendingList = removePlantRepository.findByStatusAndRemovedBy_UserId(RemovePlantStatus.PENDING, userId,previewPage);
            aprovedList = removePlantRepository.findByStatusAndRemovedBy_UserId(RemovePlantStatus.APPROVED, userId,previewPage);
            rewardEntities = userRewardRepository.findByUser_UserIdOrderByReward_RewardIdDesc(userId, previewPageRewards);
            reportedEntities = plantRepository.findByReportedBy_UserIdOrderByPlantIdDesc(userId, previewPageReported);
        }

        //Map to DTO
        List<UserRemovedPlantsStatusResponseDto> pendingPreview = userRemovedPlantsStatusMapper.toDto(pendingList);
        List<UserRemovedPlantsStatusResponseDto> approvedPreview = userRemovedPlantsStatusMapper.toDto(aprovedList);
        List<RewardPreviewResponseDto> giftPreview =rewardEntities.stream()
                .map(rewardPreviewMapper::toDto)
                .collect(Collectors.toList());

        List<ReportedPreviewResponceDto> reportPreview = reportedEntities.stream()
                .map(reportedPreviewMapper::toDto)
                .collect(Collectors.toList());

        // building up the frontend DTO
        UserProfileDashboardResponseDto dto = new UserProfileDashboardResponseDto();
        dto.setPoints(points);// set after you load user
        dto.setPendingTotal(pendingTotal);
        dto.setApprovedTotal(approvedTotal);
        dto.setGiftsTotal(giftsTotal);
        dto.setReportedTotal(reportedTotal);
        dto.setPendingPreview(pendingPreview);
        dto.setApprovedPreview(approvedPreview);
        dto.setGiftsPreview(giftPreview);
        dto.setReportedPreview(reportPreview);
        return dto;

    }

    //#################################### GET USER NAME ##################################################

    public UserNameResponseDto getUsername(Long userId){

        // check if user exist in db
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ApiException("User was not found", HttpStatus.NOT_FOUND));

        return userNameMapper.toDto(user);

    }

}
