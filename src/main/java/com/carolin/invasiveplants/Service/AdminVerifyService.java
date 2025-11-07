package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.RequestDTO.AdminVerifyRequestDTO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminVerifyService {

    private final PlantRepository plantRepository;

    public AdminVerifyService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    //service update the old status to a new status on the removed plant
    public void updateReportedPlantsStatus(Long id, PlantStatus newStatus){

        Plant removedPlant = plantRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Removed plant not found"));

        //double check so it really is reported as REMOVED before changing status
        if(removedPlant.getStatus() != PlantStatus.REMOVED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Only plants with status 'REMOVED' can be verified or declined");
        }

        removedPlant.setStatus(newStatus);
        plantRepository.save(removedPlant);
    }

}
