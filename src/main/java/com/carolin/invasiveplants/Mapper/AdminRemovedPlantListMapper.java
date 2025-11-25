package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.ResponseDTO.AdminRemovedPlantsListResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminRemovedPlantListMapper {

    public AdminRemovedPlantsListResponseDto toDto (RemovedPlant removedPlant){
        if (removedPlant == null) return null;


        return new AdminRemovedPlantsListResponseDto(
                removedPlant.getRemovedPlantId(),
                removedPlant.getReportedPlant().getSpecies().getSpeciesName(),
                removedPlant.getRemovedBy().getFirstName()+"  "+removedPlant.getRemovedBy().getLastName(),
                removedPlant.getRemovedAt(),
                removedPlant.getPhotoAfter(),
                removedPlant.getReportedPlant().getPhotoBefore());

    }

    public List<AdminRemovedPlantsListResponseDto> toDto(List<RemovedPlant> removedPlants) {
        if (removedPlants == null || removedPlants.isEmpty()) return Collections.emptyList();

        return removedPlants.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }
}

