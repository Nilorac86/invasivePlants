package com.carolin.invasiveplants.Service;


import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.ExceptionHandler.ApiException;
import com.carolin.invasiveplants.Mapper.SpeciesBasicInfoMapper;
import com.carolin.invasiveplants.Mapper.SpeciesDetailMapper;
import com.carolin.invasiveplants.Repository.SpeciesRepository;
import com.carolin.invasiveplants.ResponseDTO.SpeciesBasicInfoResponse;
import com.carolin.invasiveplants.ResponseDTO.SpeciesDetailResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesBasicInfoMapper speciesBasicInfoMapper;
    private final SpeciesDetailMapper speciesDetailMapper;


    public SpeciesService(SpeciesRepository speciesRepository, SpeciesBasicInfoMapper speciesBasicInfoMapper, SpeciesDetailMapper speciesDetailMapper) {
        this.speciesRepository = speciesRepository;
        this.speciesBasicInfoMapper = speciesBasicInfoMapper;
        this.speciesDetailMapper = speciesDetailMapper;
    }

    // ################################## SPECIES LIST ####################################################

    public List<SpeciesBasicInfoResponse> getAllSpecies() {
        try {
            // fetches all species from database
        List<Species> speciesList = speciesRepository.findAll();

        // If list is empty, throw error
        if(speciesList == null || speciesList.isEmpty()) {
        throw new ApiException("No species found in the database", HttpStatus.NOT_FOUND);
        }
        //Mapping entities to DTO and returns them
        return speciesBasicInfoMapper.toDto(speciesList);

            // Handles issues that violates database rules (FK, unique, not null)
        } catch (DataIntegrityViolationException ex) {
            throw new ApiException("database integrity violation: " + ex.getMessage(), HttpStatus.CONFLICT);


        } catch (Exception ex) {
            // catches all unexpected errors for example. nullpoints, runtime etc.
            throw new ApiException("Unexpected error while fetching species: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //#################################### Detail page for specific plant by ID ####################################################
    public SpeciesDetailResponseDTO getSpeciesById(Long id) {
        try {
            // Fetch species or throw 404 if not found.
            Species species = speciesRepository.findById(id)
                    .orElseThrow(() -> new ApiException("Species not found with id: " + id, HttpStatus.NOT_FOUND));

            // Convert entity to DTO for frontend.
            return speciesDetailMapper.toDto(species);

        } catch (ApiException ex) {
            throw ex;

        } catch (Exception ex) {
            // Catch and wrap any unexpected exceptions.
            throw new ApiException("Unexpected error fetching species details: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}