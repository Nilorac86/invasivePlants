// Mapper converting Species entity into detailed DTO.

package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.ResponseDTO.SpeciesDetailResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeciesDetailMapper {

    public SpeciesDetailResponseDTO toDto(Species species) {
        if (species == null) {
            return null;
        }

        return new SpeciesDetailResponseDTO(
                species.getSpeciesId(),
                species.getSpeciesName(),
                species.getDescription(),
                species.getSpeciesStatus(),
                species.getBiologicalCharacteristics(),
                species.getPlantHandling(),
                species.getPhoto()
        );

    }
}
