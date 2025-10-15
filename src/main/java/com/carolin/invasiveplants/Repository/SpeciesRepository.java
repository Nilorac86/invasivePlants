package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

}
