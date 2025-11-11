package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ListRemovedPlantsMapper;
import com.carolin.invasiveplants.Mapper.PlantRemovalReportMapper;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RemovePlantService {

    private static final long MAX_FILE_SIZE = 5_000_000; // 5MB max for pictures

    private final PlantRepository plantRepository;
    private final PlantRemovalReportMapper plantRemovalReportMapper;
    private final UserRepository userRepository;
    private final ListRemovedPlantsMapper listRemovedPlantsMapper;



    public RemovePlantService(PlantRepository plantRepository, PlantRemovalReportMapper plantRemovalReportMapper, UserRepository userRepository, ListRemovedPlantsMapper listRemovedPlantsMapper) {
        this.plantRepository = plantRepository;
        this.plantRemovalReportMapper = plantRemovalReportMapper;
        this.userRepository = userRepository;
        this.listRemovedPlantsMapper = listRemovedPlantsMapper;
    }

    // ############################### REMOVED PLANTS FORM #####################################################

    // Method for plant removal
    @Transactional
    public PlantRemovalReportResponseDto updatePlantReportAfterRemoval(Long plantId,
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

        // Converts the multipartfile to byte for the database to save.
        plant.setPhotoAfter(photoAfter.getBytes());

        int remainingCount = plant.getCount() - removedCount;
        if (remainingCount < 0) {
            throw new ApiException("You cannot remove more plants than reported", HttpStatus.BAD_REQUEST);
        }

        // Sets the information from the form
        plant.setCount(remainingCount);
        plant.setPlantId(plantId);
        plant.setRemovedBy(user); // Sets user automatic
        plant.setStatus(PlantStatus.REMOVED); // Change status

        // Saves the information user put in to the form.
        plantRepository.save(plant);

        // Return the resposeDto from the mapper, what the user gets to see.
        return plantRemovalReportMapper.toResponseDto(plant);
    }


    // ############################### LIST REMOVED PLANTS #######################################################

    /**
     * Retrieves all plants with status REMOVED from the database.
     * @return list of removed plants mapped to DTOs
     * @throws ApiException if no removed  plants are found
     */
    public List<ListRemovedPlantsResponseDTO> getAllRemovedPlants() {
        List<Plant> removedPlants = plantRepository.findByStatus(PlantStatus.REMOVED);

        // If no removed plants are found, throw API exception
        if (removedPlants == null || removedPlants.isEmpty()) {
            throw new ApiException("No removed plants found in the database.", HttpStatus.NOT_FOUND);
        }

        // Map to DTOs and return the list
        return listRemovedPlantsMapper.toDto(removedPlants);
    }



}
