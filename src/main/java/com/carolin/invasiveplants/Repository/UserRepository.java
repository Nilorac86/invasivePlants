package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user through email
    Optional<User> findByEmail(String email);


    //If email exist in database.
    boolean existsByEmail(String email);

}
