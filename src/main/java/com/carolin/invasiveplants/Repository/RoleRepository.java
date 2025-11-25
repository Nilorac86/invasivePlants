package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);
}
