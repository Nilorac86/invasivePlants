package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Plant;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import com.carolin.invasiveplants.Enum.PlantStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {


    // Fetch all plants with status = REMOVED
    List<RemovedPlant> findByStatus(PlantStatus status);

    // count total reported plants by user
    long countByReportedBy_UserId(Long userId);

    List<Plant> findByReportedBy_UserIdOrderByPlantIdDesc(Long userId, Sort sort);
    List<Plant> findByReportedBy_UserIdOrderByPlantIdDesc(Long userId, Pageable pageable);
}
