package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.ResponseDTO.SpeciesBasicInfoResponse;
import com.carolin.invasiveplants.Service.SpeciesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/plants")
@RestController
public class SpeciesController {

    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }


    @GetMapping("/info")
    public ResponseEntity<List<SpeciesBasicInfoResponse>> getAllSpecies() {

        List<SpeciesBasicInfoResponse> speciesBasicInfoResponses = speciesService.getAllSpecies();

        return ResponseEntity.ok(speciesBasicInfoResponses);
    }

}
