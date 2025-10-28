package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.ResponseDTO.ReportPlantFormResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReportPlantFormMapping {

    // Method to map entity - response DTO
    public ReportPlantFormResponseDTO toResponseDTO(Plant report) {

        return new ReportPlantFormResponseDTO(
                report.getDateTime(),
                report.getCount(),
                report.getLocation().getCity(),
                report.getSpecies().getSpeciesName(),
                report.getStatus().name(),
                report.getLocation().getLatitude(),
                report.getLocation().getLongitude(),
                report.getPhotoBefore()
        );
    }
}

