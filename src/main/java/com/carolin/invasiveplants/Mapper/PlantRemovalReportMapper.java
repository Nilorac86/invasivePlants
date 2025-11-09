package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Plant;

import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PlantRemovalReportMapper {

    public PlantRemovalReportResponseDto toResponseDto(Plant plant) {

        if (plant == null) {
            return null;
        }
        return new PlantRemovalReportResponseDto(
                plant.getPlantId(),
                plant.getSpecies().getSpeciesName(),
                plant.getPhotoAfter(),
                plant.getStatus().name(),
                plant.getDateTime()
        );

    }
}