package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.*;
import com.carolin.invasiveplants.Enum.NotificationType;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Enum.RemovePlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.AdminAddRewardMapper;
import com.carolin.invasiveplants.Mapper.AdminRemovedPlantListMapper;
import com.carolin.invasiveplants.Mapper.ListRewardsMapper;
import com.carolin.invasiveplants.Repository.NotificationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.RemovePlantRepository;
import com.carolin.invasiveplants.Repository.RewardRepository;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
import com.carolin.invasiveplants.RequestDTO.AdminAddRewardRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminAddRewardResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminRemovedPlantsListResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final PlantRepository plantRepository;
    private final NotificationRepository notificationRepository;
    private final RemovePlantRepository removePlantRepository;

    private final RewardRepository rewardRepository;
    private final AdminAddRewardMapper adminAddRewardMapper;
    private final AdminRemovedPlantListMapper adminRemovedPlantListMapper;
    private final ListRewardsMapper listRewardsMapper;

    

    public AdminService(PlantRepository plantRepository, NotificationRepository notificationRepository, RemovePlantRepository removePlantRepository, RewardRepository rewardRepository, AdminAddRewardMapper adminAddRewardMapper, AdminRemovedPlantListMapper adminRemovedPlantListMapper, ListRewardsMapper listRewardsMapper) {
        this.plantRepository = plantRepository;
        this.notificationRepository = notificationRepository;
        this.removePlantRepository = removePlantRepository;
        this.rewardRepository = rewardRepository;
        this.adminAddRewardMapper = adminAddRewardMapper;
        this.adminRemovedPlantListMapper = adminRemovedPlantListMapper;
        this.listRewardsMapper = listRewardsMapper;
    }


    // ##################################### ADMIN VERIFY REMOVED PLANT #######################################

    //service update the old status to a new status on the removed plant
    @Transactional
    public void updateReportedPlantsStatus(Long removedPlantId, RemovePlantStatus newStatus) {

        if(newStatus == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "new status must be provided");
        }

        //Find the removed plant associated with the reported plant
        RemovedPlant removedPlant = removePlantRepository.findById(removedPlantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "removed plant record not found"));


        //Hämtar rapoteradPlant vi relation på removedPlant
        Plant reportedPlant = removedPlant.getReportedPlant();
        if(reportedPlant == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Associated reported plant not found");
        }

        //double check so it really is reported as PENDING before changing status
        if (removedPlant.getStatus() != RemovePlantStatus.PENDING) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Only plants with status 'PENDING' can be verified or declined");
        }

        // Sätter ny status på RemovedPlant( men sparas senare)
        removedPlant.setStatus(newStatus);

        //Update reportedPlant status depending on new status and counts
        if (newStatus == RemovePlantStatus.REJECTED) {
            reportedPlant.setCount(reportedPlant.getCount() + removedPlant.getCount());

           // Determin correct status based on remaining count
           if(reportedPlant.getCount() == reportedPlant.getOrginalCount()){
               // mo plants have been removed att all
               reportedPlant.setStatus(PlantStatus.REGISTERED);
           }else{
               // some removal are approved from before
               reportedPlant.setStatus(PlantStatus.PARTLYREMOVED);
           }

            // If removal is approved status changes depending on if its removed or partlyremoved
        } else if (newStatus == RemovePlantStatus.APPROVED) {
            if (reportedPlant.getCount() == 0) { // If count is 0 status will be VERIFIED
                reportedPlant.setStatus(PlantStatus.VERIFIED);
        } else {
                reportedPlant.setStatus(PlantStatus.PARTLYREMOVED); // If there's count left, it will still be partlyremoved.
            }

    }
        // Save the new information in reported plant table.
        //plantRepository.save(reportedPlant);

        //Notification for user who removed the plant
        User removedByUser= removedPlant.getRemovedBy();

        Notification notification = new Notification();

        notification.setUser(removedByUser);
        notification.setTime(LocalDateTime.now());

        //sending the user that removed plant a message depending on admin, approve or decline
        if (newStatus == RemovePlantStatus.APPROVED){

            notification.setMessage("Din rapporterade borttagna växt har nu verifierats! Du har tjänat poäng.");
            notification.setNotificationType(NotificationType.SUCCESS);

        } else if (newStatus == RemovePlantStatus.REJECTED) {
            notification.setMessage("Din rapport om borttagen växt godkändes inte.");
            notification.setNotificationType(NotificationType.WARNING);
        }

        try {
            removePlantRepository.save(removedPlant);
            plantRepository.save(reportedPlant);
            notificationRepository.save(notification);
        } catch (DataIntegrityViolationException e) {
            // Logga detaljer och kasta om med relevant info
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Database integrity violation: " + e.getMessage(), e);
        }

    }

    // ##################################### ADMIN ADD A REWARD #######################################

    public AdminAddRewardResponseDTO adminAddNewReward(AdminAddRewardRequestDTO dto){

        Reward reward = adminAddRewardMapper.mapToEntity(dto);
        Reward savedReward = rewardRepository.save(reward);
        return adminAddRewardMapper.responseDTO(savedReward);
    }


   // ##################################### ADMIN REMOVED PLANT LIST ######################################

    public List<AdminRemovedPlantsListResponseDto> getAllremovedPlantList (){

        List<RemovedPlant> removedPlantsList = removePlantRepository.findByStatus(RemovePlantStatus.PENDING);

            // If there´s no removed plants reported.
            if(removedPlantsList == null || removedPlantsList.isEmpty()){
                throw new ApiException("No removed plants found in the database.", HttpStatus.NOT_FOUND);
            }

            return adminRemovedPlantListMapper.toDto(removedPlantsList);
    }
    // ##################################### LIST REWARDS #######################################

    public List<ListRewardResponseDTO>listRewads(User user){

        int userPoints = (user == null || user.getPoints() == null) ? 0 : user.getPoints();

        return rewardRepository.findAll().stream()
                .filter(r -> r.getRewardAmount() != null && r.getRewardAmount() > 0)
                .sorted(Comparator.comparing(Reward::getPoints, Comparator.nullsLast(Integer::compareTo)))
                .map(r -> listRewardsMapper.toDto(r, userPoints))
                .collect(Collectors.toList());


    }

}
