package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("SELECT p FROM Plant p LEFT JOIN FETCH p.removedBy WHERE p.plantId = :id")
    Optional<Plant> findByIdWithRemovedBy(@Param("id")Long id);

}
