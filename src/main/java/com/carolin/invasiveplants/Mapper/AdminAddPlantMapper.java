package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.RequestDTO.AdminAddPlantRequestDto;
import com.carolin.invasiveplants.ResponseDTO.AdminAddPlantResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AdminAddPlantMapper {

    public AdminAddPlantResponseDto toDto(Species species){

        if (species == null){
            return null;
        }

        return new AdminAddPlantResponseDto(
                species.getSpeciesName(),
                species.getDescription(),
                species.getSpeciesStatus(),
                species.getBiologicalCharacteristics(),
                species.getPlantHandling(),
                species.getPhoto()
        );

    }

    public Species toEntity(AdminAddPlantRequestDto dto){

        if(dto == null){
            return null;
        }

        Species species = new Species();
        species.setSpeciesName(dto.getSpeciesName());
        species.setDescription(dto.getDescription());
        species.setSpeciesStatus(dto.getSpeciesStatus());
        species.setBiologicalCharacteristics(dto.getBiologicalCharacteristics());
        species.setPlantHandling(dto.getPlantHandling());

        return species;
    }
}
