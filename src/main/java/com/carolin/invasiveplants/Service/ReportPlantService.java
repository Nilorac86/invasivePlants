package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Location;
import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.ListReportedPlantsmapper;
import com.carolin.invasiveplants.Mapper.ReportPlantFormMapping;
import com.carolin.invasiveplants.Repository.LocationRepository;
import com.carolin.invasiveplants.Repository.PlantRepository;
import com.carolin.invasiveplants.Repository.SpeciesRepository;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.ResponseDTO.ListReportedPlantsResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.ReportPlantFormResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportPlantService {

    private static final long MAX_FILE_SIZE = 5_000_000; // 5MB max for pictures
    private static final int MAX_CITY_LENGTH = 30; // Max 30 tecken för stad

    private final ReportPlantFormMapping reportPlantFormMapping;
    private final SpeciesRepository speciesRepository;
    private final PlantRepository plantRepository;
    private final LocationRepository locationRepository;
    private final ListReportedPlantsmapper listReportedPlantsmapper;
    private final UserRepository userRepository;

    public ReportPlantService(ReportPlantFormMapping reportPlantFormMapping,
                              SpeciesRepository speciesRepository,
                              PlantRepository plantsRepository,
                              LocationRepository locationRepository, ListReportedPlantsmapper listReportedPlantsmapper, UserRepository userRepository) {
        this.reportPlantFormMapping = reportPlantFormMapping;
        this.speciesRepository = speciesRepository;
        this.plantRepository = plantsRepository;
        this.locationRepository = locationRepository;
        this.listReportedPlantsmapper = listReportedPlantsmapper;
        this.userRepository = userRepository;
    }

    // ############################# LIST REPORTED PLANTS #########################################

    public List<ListReportedPlantsResponseDTO> getAllReportedPlants(){

        List<Plant> reportedPlantList = plantRepository.findAll();

        // If list is empty, throw error
        if(reportedPlantList == null || reportedPlantList.isEmpty()) {
            throw new ApiException("No reported plants was found in the database", HttpStatus.NOT_FOUND);
        }

        //Mapping entities to DTO and returns them
        return listReportedPlantsmapper.toDto(reportedPlantList);

    }


    // ################################## FORM REPORT PLANTS ############################################

    @Transactional
    public ReportPlantFormResponseDTO createReport(
            String speciesIdStr,
            String latitudeStr,
            String longitudeStr,
            String city,
            String countStr,
            MultipartFile photoBefore,
            User user) throws IOException {

        // Validate and convert all input from text to correct data types
        Long speciesId = validateAndParseSpeciesId(speciesIdStr);
        BigDecimal latitude = validateAndParseCoordinate(latitudeStr, "latitude");
        BigDecimal longitude = validateAndParseCoordinate(longitudeStr, "longitude");
        Integer count = validateAndParseCount(countStr);
        validateCity(city);
        byte[] photoBytes = validateAndProcessPhoto(photoBefore);

        // find species
        Species species = findSpeciesOrThrow(speciesId);

        // create and save location in database
        Location location = new Location(latitude, longitude, city);
        locationRepository.save(location);

        // create and save report with collected data
        Plant report = createPlantReport(count, location, photoBytes, species, user);
        plantRepository.save(report);

        //Give user points for reporting a plant
        Integer reward = (species.getPointsReport() == null) ? 0 : species.getPointsReport();
        if(reward > 0){
            Integer current = (user.getPoints() == null) ? 0 : user.getPoints();
            user.setPoints(current + reward);
            userRepository.save(user);
        }

        //return to frontend
        return reportPlantFormMapping.toResponseDTO(report);
    }


    //validate species Id (for safety against manipulation of data)
    private Long validateAndParseSpeciesId(String speciesIdStr) {
        if (speciesIdStr == null || speciesIdStr.isBlank()) {
            throw new ApiException("Species ID is required", HttpStatus.BAD_REQUEST);
        }
            //change to correct data type with parse for example from "5" to 5.
            return Long.parseLong(speciesIdStr);
    }

    //validate coordinates
    private BigDecimal validateAndParseCoordinate(String coordinateStr, String fieldName) {
        if (coordinateStr == null || coordinateStr.isBlank()) {
            throw new ApiException(fieldName + " is required", HttpStatus.BAD_REQUEST);
        }
        try { //change text "59.12345" to BigDecimal 59.12345 exact decimal.
            BigDecimal coordinate = new BigDecimal(coordinateStr);

            //split at dot: "59.12345" = 59, 12345
            String[] parts = coordinateStr.split("\\.");
            // Validate that at least five decimals exist after dot (format xxx.yyyyy)
            if (parts.length != 2 || parts[1].length() < 5) {
                throw new ApiException(
                        fieldName + " must have at least 5 decimal places (format: xxx.yyyyy)",
                        HttpStatus.BAD_REQUEST
                );
            }

            return coordinate;
        } catch (NumberFormatException e) {
            //If user put "abc" or "59,12345" (, instead of .)
            throw new ApiException("Invalid " + fieldName + " format", HttpStatus.BAD_REQUEST);
        }
    }

    //validate plant count
    private Integer validateAndParseCount(String countStr) {
        if (countStr == null || countStr.isBlank()) {
            throw new ApiException("Count is required", HttpStatus.BAD_REQUEST);
        }
        try {
            Integer count = Integer.parseInt(countStr);
            if (count < 1) {
                throw new ApiException("Count must be at least 1", HttpStatus.BAD_REQUEST);
            }
            return count;
        } catch (NumberFormatException e) {
            throw new ApiException("Invalid count format", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateCity(String city) {
        if (city == null || city.isBlank()) {
            throw new ApiException("City is required", HttpStatus.BAD_REQUEST);
        }
        if (city.length() > MAX_CITY_LENGTH) {
            throw new ApiException(
                    "City name cannot exceed " + MAX_CITY_LENGTH + " characters",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    //validation of photo, filetype and size
    private byte[] validateAndProcessPhoto(MultipartFile photo) throws IOException {
        if (photo == null || photo.isEmpty()) {
            throw new ApiException(
                    "Image is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        //check size and compare so it is not bigger than 5MB
        if (photo.getSize() > MAX_FILE_SIZE) {
            throw new ApiException(
                    "Image size cannot exceed 5MB",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validate filetype so it is a photo
        String contentType = photo.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ApiException(
                    "File must be an image",
                    HttpStatus.BAD_REQUEST
            );
        }

        //picture is converted to bytes so it can be saved in the database
        return photo.getBytes();
    }

    //find plant in database
    private Species findSpeciesOrThrow(Long speciesId) {
        return speciesRepository.findById(speciesId)
                .orElseThrow(() -> new ApiException(
                        "Species not found with ID: " + speciesId,
                        HttpStatus.NOT_FOUND
                ));
    }

    //create a new plant report with all information
    private Plant createPlantReport(Integer count, Location location, byte[] photoBytes,
                                    Species species, User user) {
        Plant report = new Plant();
        report.setOrginalCount(count);
        report.setCount(count);
        report.setLocation(location);
        report.setPhotoBefore(photoBytes);
        report.setSpecies(species);
        report.setReportedBy(user);
        report.setStatus(PlantStatus.REGISTERED);
        report.setDateTime(LocalDateTime.now());
        return report;
    }




}