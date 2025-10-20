package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Hitta användare via email
    Optional<User> findByEmail(String email);


    //kollar om email finns i databasen..
    boolean existsByEmail(String email);

}
