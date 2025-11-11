package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.PlantRemovalReportRequestDto;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import com.carolin.invasiveplants.Service.RemovePlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/remove-plant")
@RestController
public class RemovePlantController {

    private final RemovePlantService plantService;


    public RemovePlantController(RemovePlantService plantService) {
        this.plantService = plantService;
    }


    // Plant removal method uses modelattribute to handle multiple input (text, file(photo))
    @PutMapping("/form")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PlantRemovalReportResponseDto> removePlants(
            @ModelAttribute PlantRemovalReportRequestDto plantRemovalReportRequestDto,
            @AuthenticationPrincipal User user) throws IOException {

        // ResponeDto to send back to frontend.
        PlantRemovalReportResponseDto response = plantService.updatePlantReportAfterRemoval(
                plantRemovalReportRequestDto.getPlantId(),
                plantRemovalReportRequestDto.getPhotoAfter(),
                plantRemovalReportRequestDto.getRemovedCount(),
                user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

