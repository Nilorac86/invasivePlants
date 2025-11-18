package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.AdminAddRewardRequestDTO;
import com.carolin.invasiveplants.RequestDTO.AdminVerifyRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminAddRewardResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminRemovedPlantsListResponseDto;
import com.carolin.invasiveplants.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

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
    public ResponseEntity<List<AdminRemovedPlantsListResponseDto>> adminRemovedPlantsList(){

        List<AdminRemovedPlantsListResponseDto> adminRemovedPlantsListResponseDtos =
                adminService.getAllremovedPlantList();

        return ResponseEntity.ok(adminRemovedPlantsListResponseDtos);
    }
}
