package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Notification;
import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Enum.NotificationType;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Repository.NotificationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@Transactional
public class AdminService {

    private final PlantRepository plantRepository;
    private final NotificationRepository notificationRepository;

    public AdminService(PlantRepository plantRepository, NotificationRepository notificationRepository) {
        this.plantRepository = plantRepository;
        this.notificationRepository = notificationRepository;
    }


    // ##################################### ADMIN VERIFY REMOVED PLANT #######################################

    //service update the old status to a new status on the removed plant
    public void updateReportedPlantsStatus(Long id, PlantStatus newStatus){

        Plant removedPlant = plantRepository.findByIdWithRemovedBy(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Removed plant not found"));

        //double check so it really is reported as REMOVED before changing status
        if(removedPlant.getStatus() != PlantStatus.REMOVED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Only plants with status 'REMOVED' can be verified or declined");
        }

        removedPlant.setStatus(newStatus);
        plantRepository.save(removedPlant);

        //Create and save a notification for the user
        Notification notification = new Notification();
        notification.setUser(removedPlant.getRemovedBy());
        notification.setTime(LocalDateTime.now());

        //sending the user that removed plant a message depending on admin, approve or decline
        if (newStatus == PlantStatus.VERIFIED){
            notification.setMessage("Your removed plant has been verified! You have earned points");
            notification.setNotificationType(NotificationType.SUCCESS);
        } else if (newStatus == PlantStatus.REGISTERED) {
            notification.setMessage("Your removed plant report was declined.");
            notification.setNotificationType(NotificationType.WARNING);
        }

        notificationRepository.save(notification);
    }

}
