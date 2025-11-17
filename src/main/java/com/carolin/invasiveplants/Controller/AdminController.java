package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.AdminAddRewardRequestDTO;
import com.carolin.invasiveplants.RequestDTO.AdminVerifyRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.AdminAddRewardResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
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

    private final AdminService adminVerifyService;

    public AdminController(AdminService adminVerifyService) {
        this.adminVerifyService = adminVerifyService;
    }

// ################################ ADMIN VERIFY REMOVED PLANT #############################################

    @PutMapping("/verify")
    @PreAuthorize("hasRole('ADMIN')") // make sure only users with role admin get access
    public ResponseEntity<Void>validateReportedPlants(
            @RequestBody AdminVerifyRequestDTO requestDTO,
            @AuthenticationPrincipal User user){

        adminVerifyService.updateReportedPlantsStatus(
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

        AdminAddRewardResponseDTO responseDTO = adminVerifyService.adminAddNewReward(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    // ################################ LIST REWARDS #############################################

    @GetMapping("/list-rewards")
    public ResponseEntity<List<ListRewardResponseDTO>>listRewards(){
        return ResponseEntity.ok(adminVerifyService.listRewads());

    }
}
