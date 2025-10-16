package com.carolin.invasiveplants.Service;


import com.carolin.invasiveplants.Entity.Species;
import com.carolin.invasiveplants.Mapper.SpeciesBasicInfoMapper;
import com.carolin.invasiveplants.Repository.SpeciesRepository;
import com.carolin.invasiveplants.ResponseDTO.SpeciesBasicInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesBasicInfoMapper speciesBasicInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(SpeciesService.class);

    public SpeciesService(SpeciesRepository speciesRepository, SpeciesBasicInfoMapper speciesBasicInfoMapper) {
        this.speciesRepository = speciesRepository;
        this.speciesBasicInfoMapper = speciesBasicInfoMapper;

    }

    public List<SpeciesBasicInfoResponse> getAllSpecies() {

        try{
            List<Species> speciesList = speciesRepository.findAll();

            //structured logg
            logger.info(
                    "action=getAllSpecies status=SUCCESS count={} message='Fetched all species successfully'",
                    speciesList.size());

            return speciesBasicInfoMapper.toDto(speciesList);

        }catch (Exception e){

            //structured logg
            logger.error(
                    "action=getAllSpecies status=FAIL error='{}' message='Failed to fetch species from database'",
                    e.getMessage(),
                    e
            );
        throw e;
        }

    }
}


