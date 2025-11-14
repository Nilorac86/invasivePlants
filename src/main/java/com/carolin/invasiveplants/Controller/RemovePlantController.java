package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.PlantRemovalReportRequestDto;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import com.carolin.invasiveplants.Service.RemovePlantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/remove-plant")
@RestController
public class RemovePlantController {

    private final RemovePlantService removePlantService;


    public RemovePlantController(RemovePlantService plantService) {
        this.removePlantService = plantService;
    }



// ########################## REMOVE PLANT FORM ##########################################################

    // Plant removal method uses modelattribute to handle multiple input (text, file(photo))
    @PostMapping("/form")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PlantRemovalReportResponseDto> removePlants(@Valid
            @ModelAttribute PlantRemovalReportRequestDto plantRemovalReportRequestDto,
            @AuthenticationPrincipal User user) throws IOException {

        // ResponeDto to send back to frontend.
        PlantRemovalReportResponseDto response = removePlantService.removePlantForm(
                plantRemovalReportRequestDto.getPlantId(),
                plantRemovalReportRequestDto.getPhotoAfter(),
                plantRemovalReportRequestDto.getRemovedCount(),
                user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    // ############################ LIST REMOVED PLANTS ##################################################

//    @GetMapping("/list")
//    public ResponseEntity<List<ListRemovedPlantsResponseDTO>> getAllRemovedPlants(){
//
//        List<ListRemovedPlantsResponseDTO> listRemovedPlantsResponseDTOS = removePlantService.getAllRemovedPlants();
//
//        return ResponseEntity.ok(listRemovedPlantsResponseDTOS);
//    }
}

