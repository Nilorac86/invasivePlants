package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Notification;
import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Enum.NotificationType;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Repository.NotificationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.RemovePlantRepository;
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
    private final RemovePlantRepository removePlantRepository;

    public AdminService(PlantRepository plantRepository, NotificationRepository notificationRepository, RemovePlantRepository removePlantRepository) {
        this.plantRepository = plantRepository;
        this.notificationRepository = notificationRepository;
        this.removePlantRepository = removePlantRepository;
    }


    // ##################################### ADMIN VERIFY REMOVED PLANT #######################################

    //service update the old status to a new status on the removed plant
    public void updateReportedPlantsStatus(Long id, PlantStatus newStatus){

        //Find the reported plant by ID, throw 404 if not found
        Plant reportedPlant = plantRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reported plant not found"));

        //Find the removed plant associated with the reported plant
        RemovedPlant removedPlant = removePlantRepository.findByReportedPlantId(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "removed plant record not found"));


        //double check so it really is reported as REMOVED before changing status
        if(removedPlant.getStatus() != PlantStatus.REMOVED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Only plants with status 'REMOVED' can be verified or declined");
        }

        removedPlant.setStatus(newStatus);
        removePlantRepository.save(removedPlant);

        //Notification for user who removed the plant
        User removedByUser= removedPlant.getRemovedBy();

        Notification notification = new Notification();

        notification.setUser(removedByUser);
        notification.setTime(LocalDateTime.now());

        //sending the user that removed plant a message depending on admin, approve or decline
        if (newStatus == PlantStatus.VERIFIED){
            notification.setMessage("Din rapporterade borttagna växt har nu verifierats! Du har tjänat poäng.");
            notification.setNotificationType(NotificationType.SUCCESS);
        } else if (newStatus == PlantStatus.REGISTERED) {
            notification.setMessage("Din rapport om borttagen växt godkändes inte.");
            notification.setNotificationType(NotificationType.WARNING);
        }

        notificationRepository.save(notification);
    }

}
