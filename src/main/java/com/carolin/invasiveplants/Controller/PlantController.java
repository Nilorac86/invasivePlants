package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.PlantRemovalReportRequestDto;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import com.carolin.invasiveplants.Service.PlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reported-plants")
@RestController
public class PlantController {

    private final PlantService plantService;


    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }


    @PutMapping("/remove-form")
    public ResponseEntity<PlantRemovalReportResponseDto> removePlants(
            @ModelAttribute PlantRemovalReportRequestDto plantRemovalReportRequestDto,
            @AuthenticationPrincipal User user) throws IOException {

        PlantRemovalReportResponseDto response = plantService.updatePlantReportAfterRemoval(
                plantRemovalReportRequestDto.getPlantId(),
                plantRemovalReportRequestDto.getPhotoAfter(),
                plantRemovalReportRequestDto.getRemovedCount(),
                user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

