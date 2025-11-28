package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.RequestDTO.AdminAddPlantRequestDto;
import com.carolin.invasiveplants.ResponseDTO.AdminAddPlantResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
public class AdminAddPlantMapper {

    public AdminAddPlantResponseDto toDto(Species species){

        if (species == null){
            return null;
        }

        // You can't just pass byte[] as MultipartFile,
        // usually for response you send something else like a URL or base64 string.
        // For example, encode photo bytes to Base64 String:
        String base64Photo = null;
        if (species.getPhoto() != null) {
            base64Photo = Base64.getEncoder().encodeToString(species.getPhoto());
        }

        return new AdminAddPlantResponseDto(
                species.getSpeciesName(),
                species.getDescription(),
                species.getSpeciesStatus(),
                species.getBiologicalCharacteristics(),
                species.getPlantHandling(),
                base64Photo,
                species.getPointsReport(),
                species.getPointsRemove()
        );

    }

    public Species toEntity(AdminAddPlantRequestDto dto) throws IOException{

        if(dto == null){
            return null;
        }

        Species species = new Species();
        species.setSpeciesName(dto.getSpeciesName());
        species.setDescription(dto.getDescription());
        species.setSpeciesStatus(dto.getSpeciesStatus());
        species.setBiologicalCharacteristics(dto.getBiologicalCharacteristics());
        species.setPlantHandling(dto.getPlantHandling());

        MultipartFile photo = dto.getPhoto();
        if(photo != null && !photo.isEmpty()){
            species.setPhoto(photo.getBytes());
        }

        species.setPointsReport(dto.getPointsReport());
        species.setPointsRemove(dto.getPointsRemove());

        return species;
    }
}
