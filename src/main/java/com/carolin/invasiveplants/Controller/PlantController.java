package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Service.PlantService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/plants")
@RestController
public class PlantController {

    private final PlantService plantService;


    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }


}
