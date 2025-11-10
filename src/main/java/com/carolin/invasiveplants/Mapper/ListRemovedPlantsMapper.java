package com.carolin.invasiveplants.Mapper;


import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListRemovedPlantsMapper {

    public ListRemovedPlantsResponseDTO toDTO (Plant plant){
        if (plant == null) return null;

        String userName = "Unknown";

        // Check so that user is not null
        if (plant.getRemovedBy() != null) {
            String firstName = plant.getRemovedBy().getFirstName() != null
                    ? plant.getRemovedBy().getFirstName()
                    : "";
            String lastName = plant.getRemovedBy().getLastName() != null
                    ? plant.getRemovedBy().getLastName()
                    : "";

            userName = (firstName + " " + lastName).trim();
        }


        return new ListRemovedPlantsResponseDTO(
                plant.getSpecies().getSpeciesName(),
                userName,
                plant.getDateTime(),
                plant.getPhotoAfter());
    }

    public List<ListRemovedPlantsResponseDTO> toDto(List<Plant> removedPlants) {
        if (removedPlants == null || removedPlants.isEmpty()) return Collections.emptyList();

        return removedPlants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
}
