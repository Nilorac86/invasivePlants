package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.ResponseDTO.ListReportedPlantsResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListReportedPlantsmapper {

    public ListReportedPlantsResponseDTO toDTO (Plant plant){

        if (plant == null) {
            return null;
        }

        return new ListReportedPlantsResponseDTO(
                plant.getSpecies().getSpeciesName(),
                plant.getLocation().getCity(),
                plant.getDateTime(),
                plant.getPhotoBefore(),
                plant.getCount()
        );
    }

    public List<ListReportedPlantsResponseDTO> toDto(List<Plant> reportedPlants){

        if (reportedPlants == null) {
            return Collections.emptyList();
        }

        return reportedPlants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

}
