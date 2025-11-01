package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.RequestDTO.ReportPlantFormRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.ListReportedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.ReportPlantFormResponseDTO;
import com.carolin.invasiveplants.Service.ListReportedPlantService;
import com.carolin.invasiveplants.Service.ReportPlantFormService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reportedPlants")
public class ReportPlantController {

    private final ReportPlantFormService reportPlantFormService;
    private final ListReportedPlantService listReportedPlantService;

    public ReportPlantController(ReportPlantFormService reportPlantFormService, ListReportedPlantService listReportedPlantService) {
        this.reportPlantFormService = reportPlantFormService;
        this.listReportedPlantService = listReportedPlantService;
    }


    @PostMapping("/form")
    @PreAuthorize("isAuthenticated()")// ensure only logged-in users can call this endpoint
    public ResponseEntity<ReportPlantFormResponseDTO> createReport(
            @RequestBody @Valid ReportPlantFormRequestDTO dto,
            @AuthenticationPrincipal User user){ // logged-in user injected

        ReportPlantFormResponseDTO response = reportPlantFormService.createRaport(dto,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/listAllReportedPlants")
    public ResponseEntity<List<ListReportedPlantsResponseDTO>> getAllPlants(){

        List<ListReportedPlantsResponseDTO> listReportedPlantsResponseDTOS = listReportedPlantService.getAllReportedPlants();

        return ResponseEntity.ok(listReportedPlantsResponseDTOS);
    }
}
