package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Location;
import com.carolin.invasiveplants.Entity.RemovedPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemovePlantReository extends JpaRepository<RemovedPlant, Long> {
}


