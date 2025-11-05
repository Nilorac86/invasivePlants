package com.carolin.invasiveplants.Mapper;


import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.ResponseDTO.SpeciesBasicInfoResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class SpeciesBasicInfoMapper {

    public SpeciesBasicInfoResponse toDto(Species species) {

        if (species == null) {
            return null;

        }

        return new SpeciesBasicInfoResponse(
                species.getSpeciesId(),
                species.getSpeciesName(),
                species.getPhoto(),
                species.getDescription());

    }

    public List<SpeciesBasicInfoResponse> toDto(List<Species> speciesList) {
        if (speciesList == null) {
            return Collections.emptyList();
        }

        return speciesList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }
}