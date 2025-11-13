package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.RemovedPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemovePlantRepository extends JpaRepository<RemovedPlant, Long> {

    Optional<RemovedPlant> findByReportedPlant_PlantId(Long reportedPlantId);

}


