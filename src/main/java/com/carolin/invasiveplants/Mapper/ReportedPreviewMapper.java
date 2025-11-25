package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.ResponseDTO.ReportedPreviewResponceDto;
import org.springframework.stereotype.Component;

@Component
public class ReportedPreviewMapper {

    public ReportedPreviewResponceDto toDto(Plant plant){

        if (plant == null) {
            return null;
        }

        return new ReportedPreviewResponceDto(
                plant.getSpecies().getSpeciesName(),
                plant.getDateTime(),
                plant.getOrginalCount(),
                plant.getCount()
        );
    }

}
