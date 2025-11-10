package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Enum.PlantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    // Fetch all plants with status = REMOVED
    List<Plant> findByStatus(PlantStatus status);
}
