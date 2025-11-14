package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.Enum.PlantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RemovePlantRepository extends JpaRepository<RemovedPlant, Long> {

    Optional<RemovedPlant> findByReportedPlant_PlantId(Long reportedPlantId);

    // Fetch all plants with status = REMOVED
    List<RemovedPlant> findByStatus(PlantStatus status);
}


