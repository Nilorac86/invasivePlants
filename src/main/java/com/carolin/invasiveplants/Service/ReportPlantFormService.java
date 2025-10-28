package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Location;
import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ReportPlantFormMapping;
import com.carolin.invasiveplants.Repository.LocationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.SpeciesRepository;
import com.carolin.invasiveplants.RequestDTO.ReportPlantFormRequestDTO;
import com.carolin.invasiveplants.ResponseDTO.ReportPlantFormResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReportPlantFormService {

    private final ReportPlantFormMapping reportPlantFormMapping;
    private final SpeciesRepository speciesRepository;
    private final PlantRepository plantsRepository;
    private final LocationRepository locationRepository;

    public ReportPlantFormService(ReportPlantFormMapping reportPlantFormMapping, SpeciesRepository speciesRepository,
                                  PlantRepository plantsRepository, LocationRepository locationRepository) {
        this.reportPlantFormMapping = reportPlantFormMapping;
        this.speciesRepository = speciesRepository;
        this.plantsRepository = plantsRepository;
        this.locationRepository = locationRepository;
    }


    @Transactional
    public ReportPlantFormResponseDTO createRaport(ReportPlantFormRequestDTO dto, User user){

        // Ensure species exist
        Species species = findSpecie(dto.getSpeciesId());

        // Create Plant entity (a new report of an invasive plant)
        Plant report = new Plant();

        report.setCount(dto.getCount());

        Location location = new Location(dto.getLatitude(),dto.getLongitude(),dto.getCity());
        locationRepository.save(location); //save location first
        report.setLocation(location);

        report.setPhotoBefore(dto.getPhotoBefore());
        report.setSpecies(findSpecie(dto.getSpeciesId()));
        report.setReportedBy(user);
        report.setStatus(PlantStatus.REGISTERED);
        report.setDateTime(LocalDateTime.now());

        plantsRepository.save(report);

        return reportPlantFormMapping.toResponseDTO(report);
    }

    // find species or throw ApiExcetion
    private Species findSpecie(Long speciesID){
        return speciesRepository.findById(speciesID)
                .orElseThrow(()-> new ApiException("species not found with ID" + speciesID, HttpStatus.NOT_FOUND ));
    }

}
