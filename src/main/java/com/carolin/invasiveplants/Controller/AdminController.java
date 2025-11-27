package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.RequestDTO.AdminAddPlantRequestDto;
import com.carolin.invasiveplants.RequestDTO.AdminAddRewardRequestDTO;
import com.carolin.invasiveplants.RequestDTO.AdminVerifyRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminAddPlantResponseDto;
import com.carolin.invasiveplants.ResponseDTO.AdminAddRewardResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminRemovedPlantsListResponseDto;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
import com.carolin.invasiveplants.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    public AdminController( AdminService adminService) {
        this.adminService = adminService;
    }

// ################################ ADMIN VERIFY REMOVED PLANT #############################################

    @PutMapping("/verify")
    @PreAuthorize("hasRole('ADMIN')") // make sure only users with role admin get access
    public ResponseEntity<Void>validateReportedPlants(
            @RequestBody AdminVerifyRequestDTO requestDTO,
            @AuthenticationPrincipal User user){

        adminService.updateReportedPlantsStatus(
                requestDTO.getRemovedPlantId(),
                requestDTO.getPlantStatus()
        );

        return ResponseEntity.noContent().build();
    }

    // ##################################### ADMIN ADD A REWARD #######################################

    @PostMapping("/add-reward")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminAddRewardResponseDTO>adminAddReward(
            @RequestBody @Valid AdminAddRewardRequestDTO requestDTO,
            @AuthenticationPrincipal User user){

        AdminAddRewardResponseDTO responseDTO = adminService.adminAddNewReward(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    // ##################################### ADMIN REMOVE PLANT LIST ###########################################

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("removed-plant/list")
    public ResponseEntity<List<AdminRemovedPlantsListResponseDto>> adminRemovedPlantsList() {

        List<AdminRemovedPlantsListResponseDto> adminRemovedPlantsListResponseDtos =
                adminService.getAllremovedPlantList();


        return ResponseEntity.ok(adminRemovedPlantsListResponseDtos);
    }

    // ##################################### ADMIN ADD INVASIVE PLANT ###########################################

    @PostMapping(
            value ="/add-plant",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<AdminAddPlantResponseDto>addInvasivePlant(
            @Valid @ModelAttribute AdminAddPlantRequestDto dto,
            BindingResult bindingResult,
            @AuthenticationPrincipal User user)throws IOException {

        log.info("Species Name: {}", dto.getSpeciesName());


        if(bindingResult.hasErrors()){
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            throw new ApiException("validation failed", HttpStatus.BAD_REQUEST);
        }

        if(dto.getPhoto()== null|| dto.getPhoto().isEmpty()){
            log.error("Photo is missing");
            throw new ApiException("photo is required",HttpStatus.BAD_REQUEST);
        }

        log.info("DTO values: speciesName={}, description={}, speciesStatus={}, biologicalCharacteristics={}, plantHandling={}",
                dto.getSpeciesName(),
                dto.getDescription(),
                dto.getSpeciesStatus(),
                dto.getBiologicalCharacteristics(),
                dto.getPlantHandling());

        try {
            AdminAddPlantResponseDto responseDto = adminService.adminAddPlant(dto, user);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error in adminAddPlant service", e);
            throw new ApiException("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
