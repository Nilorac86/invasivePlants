package com.carolin.invasiveplants.Service;


import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.Mapper.SpeciesBasicInfoMapper;
import com.carolin.invasiveplants.Repository.SpeciesRepository;
import com.carolin.invasiveplants.ResponseDTO.SpeciesBasicInfoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesBasicInfoMapper speciesBasicInfoMapper;


    public SpeciesService(SpeciesRepository speciesRepository, SpeciesBasicInfoMapper speciesBasicInfoMapper) {
        this.speciesRepository = speciesRepository;
        this.speciesBasicInfoMapper = speciesBasicInfoMapper;

    }

    public List<SpeciesBasicInfoResponse> getAllSpecies() {
        List<Species> speciesList = speciesRepository.findAll();
        return speciesBasicInfoMapper.toDto(speciesList);
    }
}


