package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ListRemovedPlantsMapper;
import com.carolin.invasiveplants.Mapper.ListReportedPlantsmapper;
import com.carolin.invasiveplants.Repository.LocationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.ResponseDTO.ListRemovedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.ListReportedPlantsResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListReportedPlantService {

    private final PlantRepository plantRepository;
    private final ListReportedPlantsmapper listReportedPlantsmapper;
    private final ListRemovedPlantsMapper listRemovedPlantsMapper;

    public ListReportedPlantService(PlantRepository plantRepository, ListReportedPlantsmapper listReportedPlantsmapper,
                                    ListRemovedPlantsMapper listRemovedPlantsMapper) {
        this.plantRepository = plantRepository;
        this.listReportedPlantsmapper = listReportedPlantsmapper;
        this.listRemovedPlantsMapper = listRemovedPlantsMapper;
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

    /**
     * Retrieves all plants with status REMOVED from the database.
     * @return list of removed plants mapped to DTOs
     * @throws ApiException if no removed  plants are found
     */
    public List<ListRemovedPlantsResponseDTO> getAllRemovedPlants() {
        List<Plant> removedPlants = plantRepository.findByStatus(PlantStatus.REMOVED);

        // If no removed plants are found, throw API exception
        if (removedPlants == null || removedPlants.isEmpty()) {
            throw new ApiException("No removed plants found in the database.", HttpStatus.NOT_FOUND);
        }

        // Map to DTOs and return the list
        return listRemovedPlantsMapper.toDto(removedPlants);
    }

}
