package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ListReportedPlantsmapper;
import com.carolin.invasiveplants.Repository.LocationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.ResponseDTO.ListReportedPlantsResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListReportedPlantService {

    private final PlantRepository plantRepository;
    private final ListReportedPlantsmapper listReportedPlantsmapper;

    public ListReportedPlantService(PlantRepository plantRepository, ListReportedPlantsmapper listReportedPlantsmapper) {
        this.plantRepository = plantRepository;
        this.listReportedPlantsmapper = listReportedPlantsmapper;
    }

    public List<ListReportedPlantsResponseDTO> getAllReportedPlants(){

        List<Plant> reportedPlantList = plantRepository.findAll();

            // If list is empty, throw error
            if(reportedPlantList == null || reportedPlantList.isEmpty()) {
                throw new ApiException("No reported plants was found in the database", HttpStatus.NOT_FOUND);
            }

            //Mapping entities to DTO and returns them
            return listReportedPlantsmapper.toDto(reportedPlantList);

    }

}
