package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.AdminVerifyRequestDTO;
import com.carolin.invasiveplants.Service.AdminVerifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

    private final AdminVerifyService adminVerifyService;

    public AdminController(AdminVerifyService adminVerifyService) {
        this.adminVerifyService = adminVerifyService;
    }


    @PutMapping("/verify")
    @PreAuthorize("hasRole('ADMIN')") // make sure only users with role admin get access
    public ResponseEntity<Void>validateReportedPlants(
            @RequestBody AdminVerifyRequestDTO requestDTO,
            @AuthenticationPrincipal User user){

        adminVerifyService.updateReportedPlantsStatus(
                requestDTO.getReportedPlantId(),
                requestDTO.getPlantStatus()
        );

        return ResponseEntity.noContent().build();
    }

}
