package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.ResponseDTO.UserRemovedPlantsStatusResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRemovedPlantsStatusMapper {

    public UserRemovedPlantsStatusResponseDto toDTO (RemovedPlant removedPlant){

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

        return new UserRemovedPlantsStatusResponseDto(
                removedPlant.getReportedPlant().getSpecies().getSpeciesName(),
                userName,
                removedPlant.getRemovedAt(),
                removedPlant.getPhotoAfter(),
                removedPlant.getStatus());

    }

    public List<UserRemovedPlantsStatusResponseDto> toDto(List<RemovedPlant> removedPlants) {
        if (removedPlants == null || removedPlants.isEmpty()) return Collections.emptyList();

        return removedPlants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

}
