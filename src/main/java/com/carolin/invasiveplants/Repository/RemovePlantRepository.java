package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Enum.RemovePlantStatus;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RemovePlantRepository extends JpaRepository<RemovedPlant, Long> {

    Optional<RemovedPlant> findByReportedPlant_PlantId(Long reportedPlantId);

    // Fetch all plants with status = REMOVED
    List<RemovedPlant> findByStatus(RemovePlantStatus status);

    // get all plants with status PENDING and APROVED conected to User id
    List<RemovedPlant> findByStatusInAndRemovedBy_UserIdOrderByRemovedAtDesc(List<RemovePlantStatus> statuses, Long userId);

    // for counts
    long countByStatusAndRemovedBy_UserId(RemovePlantStatus status, Long userId);

    // previw by single status with pageable
    List<RemovedPlant> findByStatusAndRemovedBy_UserId(RemovePlantStatus status, Long userId, Pageable pageable);

}


