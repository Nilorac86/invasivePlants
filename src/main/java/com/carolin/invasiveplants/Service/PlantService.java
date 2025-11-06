package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Repository.PlantRepository;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    private final PlantRepository plantRepository;


    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }
}
