package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ListRemovedPlantsMapper;
import com.carolin.invasiveplants.Mapper.PlantRemovalReportMapper;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.RemovePlantRepository;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RemovePlantService {

    private static final long MAX_FILE_SIZE = 5_000_000; // 5MB max for pictures

    private final PlantRepository plantRepository;
    private final PlantRemovalReportMapper plantRemovalReportMapper;
    private final ListRemovedPlantsMapper listRemovedPlantsMapper;
    private final RemovePlantRepository removePlantRepository;

    public RemovePlantService(PlantRepository plantRepository, PlantRemovalReportMapper plantRemovalReportMapper, ListRemovedPlantsMapper listRemovedPlantsMapper, RemovePlantRepository removePlantRepository) {
        this.plantRepository = plantRepository;
        this.plantRemovalReportMapper = plantRemovalReportMapper;
        this.listRemovedPlantsMapper = listRemovedPlantsMapper;
        this.removePlantRepository = removePlantRepository;
    }


    // ############################### REMOVED PLANTS FORM #####################################################

    // Method for plant removal
    @Transactional
    public PlantRemovalReportResponseDto removePlantForm(Long plantId,
                                                                       MultipartFile photoAfter, Integer removedCount,
                                                                       @AuthenticationPrincipal User user) throws IOException {

        // Checks if the plant is registered else it send message.
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ApiException("The requested resource could not be found", HttpStatus.NOT_FOUND));



        // Checks if photo not exits then the user get a message.
        if (photoAfter == null || photoAfter.isEmpty()) {
            throw new ApiException("Image must be provided", HttpStatus.BAD_REQUEST);
        }

        // Checks that photo is at photo
        String contentType = photoAfter.getContentType();
        if (contentType == null || !contentType.startsWith("image")) {
            throw new ApiException("The requested resource is not an image", HttpStatus.BAD_REQUEST);
        }

        // Checks photo max size
        if (photoAfter.getSize() > MAX_FILE_SIZE) {
            throw new ApiException("Image size cannot exceed 5MB, ", HttpStatus.BAD_REQUEST);
        }


        int remainingCount = plant.getCount() - removedCount;
        if (remainingCount < 0) {
            throw new ApiException("You cannot remove more plants than reported", HttpStatus.BAD_REQUEST);
        }

        plant.setCount(remainingCount);

        if (remainingCount == 0) {
            plant.setStatus(PlantStatus.VERIFIED);

            } else {
                plant.setStatus(PlantStatus.PARTLYREMOVED);// If reported plant is partly removed status is set as partly removed.
        }

            plantRepository.save(plant); // Update information in reported plant table.

        // Sets the information from the form
        RemovedPlant removedPlant = new RemovedPlant();

            removedPlant.setPhotoAfter(photoAfter.getBytes()); // Converts the multipartfile to byte for the database to save.
            removedPlant.setStatus(PlantStatus.REMOVED);
            removedPlant.setCount(removedCount);
            removedPlant.setRemovedBy(user); // Sets current user
            removedPlant.setRemovedAt(LocalDateTime.now());
            removedPlant.setReportedPlant(plant);


        // Saves the information user put in to the form.
        removePlantRepository.save(removedPlant);

        // Return the resposeDto from the mapper, what the user gets to see.
        return plantRemovalReportMapper.toResponseDto(removedPlant);
    }


    // ############################### LIST REMOVED PLANTS #######################################################

    /**
     * Retrieves all plants with status REMOVED from the database.
     * throws ApiException if no removed  plants are found
     *  return list of removed plants mapped to DTOs
     */

    public List<ListRemovedPlantsResponseDTO> getAllRemovedPlants(){

        List<RemovedPlant> removedPlants = removePlantRepository.findByStatus(PlantStatus.REMOVED);

        // If no removed plants are found, throw API exception
        if (removedPlants == null || removedPlants.isEmpty()) {
            throw new ApiException("No removed plants found in the database.", HttpStatus.NOT_FOUND);
        }

        // Map to DTOs and return the list
        return listRemovedPlantsMapper.toDto(removedPlants);
    }



}
