package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.ListReportedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.ReportPlantFormResponseDTO;
import com.carolin.invasiveplants.Service.ListReportedPlantService;
import com.carolin.invasiveplants.Service.ReportPlantFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reportedplants")
public class ReportPlantController {

    private final ReportPlantFormService reportPlantFormService;
    private final ListReportedPlantService listReportedPlantService;

    public ReportPlantController(ReportPlantFormService reportPlantFormService, ListReportedPlantService listReportedPlantService) {
        this.reportPlantFormService = reportPlantFormService;
        this.listReportedPlantService = listReportedPlantService;
    }

    /**
     * Creates a new plant report with photo upload.
     *
     * NOTE: This endpoint uses @RequestParam instead of @RequestBody with DTO because:
     * 1. We need to accept file uploads (MultipartFile for the photo)
     * 2. JSON format (@RequestBody) cannot handle binary file data efficiently
     * 3. multipart/form-data (used by @RequestParam) allows sending both text fields
     *    AND files in the same request
     * 4. Frontend sends data as FormData, which is the standard way to upload files
     *
     * All validation and data conversion is handled in the Service layer.
     * String parameters are parsed to correct types (Long, BigDecimal, Integer) there.
     */
    @PostMapping("/form")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReportPlantFormResponseDTO> createReport(
            @RequestParam("speciesId") String speciesId,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("city") String city,
            @RequestParam("count") String count,
            @RequestParam(value = "photoBefore", required = false) MultipartFile photoBefore,
            @AuthenticationPrincipal User user) throws IOException {

        ReportPlantFormResponseDTO response = reportPlantFormService.createReport(
                speciesId, latitude, longitude, city, count, photoBefore, user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/listallreportedplants")
    public ResponseEntity<List<ListReportedPlantsResponseDTO>> getAllPlants(){

        List<ListReportedPlantsResponseDTO> listReportedPlantsResponseDTOS = listReportedPlantService.getAllReportedPlants();

        return ResponseEntity.ok(listReportedPlantsResponseDTOS);
    }

    @GetMapping("/listremovedplants")
    public ResponseEntity<List<ListRemovedPlantsResponseDTO>> getAllRemovedPlants(){

        List<ListRemovedPlantsResponseDTO> listRemovedPlantsResponseDTOS = listReportedPlantService.getAllRemovedPlants();

        return ResponseEntity.ok(listRemovedPlantsResponseDTOS);
    }
}
