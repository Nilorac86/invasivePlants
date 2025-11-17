package com.carolin.invasiveplants.Mapper;


import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListRemovedPlantsMapper {

    public ListRemovedPlantsResponseDTO toDTO (RemovedPlant removedPlant){

        if (removedPlant == null) return null;

        String userName = "unknown";

        // Check so that user is not null
        if(removedPlant.getRemovedBy() !=null){
            String firstName = removedPlant.getRemovedBy().getFirstName() != null
                    ? removedPlant.getRemovedBy().getFirstName()
                    : "";
            String lastName = removedPlant.getRemovedBy().getLastName() != null
                    ? removedPlant.getRemovedBy().getLastName()
                    : "";

            userName = (firstName + " " + lastName).trim();
        }

        return new ListRemovedPlantsResponseDTO(
                removedPlant.getReportedPlant().getSpecies().getSpeciesName(),
                userName,
                removedPlant.getRemovedAt(),
                removedPlant.getPhotoAfter());

    }

    public List<ListRemovedPlantsResponseDTO> toDto(List<RemovedPlant> removedPlants) {
        if (removedPlants == null || removedPlants.isEmpty()) return Collections.emptyList();

        return removedPlants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

}
