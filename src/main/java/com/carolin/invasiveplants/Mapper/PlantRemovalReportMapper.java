package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Plant;

import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.ResponseDTO.PlantRemovalReportResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PlantRemovalReportMapper {

    public PlantRemovalReportResponseDto toResponseDto(RemovedPlant plant) {

        if (plant == null) {
            return null;
        }
        return new PlantRemovalReportResponseDto(
                plant.getReportedPlant().getSpecies().getSpeciesName(),
                plant.getReportedPlant().getPhotoBefore(),
                plant.getPhotoAfter(),
                plant.getStatus(),
                plant.getRemovedAt(),
                plant.getCount(),
                plant.getReportedPlant().getPlantId()
        );

    }
}