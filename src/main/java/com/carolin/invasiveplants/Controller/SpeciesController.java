package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.ResponseDTO.SpeciesBasicInfoResponse;
import com.carolin.invasiveplants.ResponseDTO.SpeciesDetailResponseDTO;
import com.carolin.invasiveplants.Service.SpeciesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/plants")
@RestController
public class SpeciesController {

    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }


    // #################################### PLANT LIST ####################################################

    @GetMapping("/info")
    public ResponseEntity<List<SpeciesBasicInfoResponse>> getAllSpecies() {

        List<SpeciesBasicInfoResponse> speciesBasicInfoResponses = speciesService.getAllSpecies();

        return ResponseEntity.ok(speciesBasicInfoResponses);
    }

    //#################################### Detail page for specific plant by ID ####################################################
    @GetMapping("/{id}")
    public ResponseEntity<SpeciesDetailResponseDTO> getSpeciesById(@PathVariable Long id) {
        SpeciesDetailResponseDTO speciesDetailResponseDTO = speciesService.getSpeciesById(id);

        return ResponseEntity.ok(speciesDetailResponseDTO);
    }

}
