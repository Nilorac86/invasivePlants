package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.PlantRemovalReportMapper;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PlantService {

    private static final long MAX_FILE_SIZE = 5_000_000; // 5MB max for pictures

    private final PlantRepository plantRepository;
    private final PlantRemovalReportMapper plantRemovalReportMapper;
    private final UserRepository userRepository;



    public PlantService(PlantRepository plantRepository, PlantRemovalReportMapper plantRemovalReportMapper, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.plantRemovalReportMapper = plantRemovalReportMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    public PlantRemovalReportResponseDto updatePlantReportAfterRemoval(Long plantId,
                                                                       MultipartFile photoAfter, Integer removedCount,
                                                                       @AuthenticationPrincipal User user) throws IOException {


        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ApiException("The requested resource could not be found", HttpStatus.NOT_FOUND));

        User userEntity = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        if (photoAfter == null || photoAfter.isEmpty()) {
            throw new ApiException("Image must be provided", HttpStatus.BAD_REQUEST);
        }

        String contentType = photoAfter.getContentType();
        if (contentType == null || !contentType.startsWith("image")) {
            throw new ApiException("The requested resource is not an image", HttpStatus.BAD_REQUEST);
        }

        if (photoAfter.getSize() > MAX_FILE_SIZE) {
            throw new ApiException("Image size cannot exceed 5MB, ", HttpStatus.BAD_REQUEST);
        }

        plant.setPhotoAfter(photoAfter.getBytes());

        int remainingCount = plant.getCount() - removedCount;
        if (remainingCount < 0) {
            throw new ApiException("You cannot remove more plants than reported", HttpStatus.BAD_REQUEST);
        }

        plant.setCount(remainingCount);
        plant.setPlantId(plantId);
        plant.setRemovedBy(user);
        plant.setStatus(PlantStatus.REMOVED);

        plantRepository.save(plant);

        System.out.println("=== REMOVE PLANTS END ===");

        return plantRemovalReportMapper.toResponseDto(plant);
    }
}
