package com.carolin.invasiveplants.Repository;

import com.carolin.invasiveplants.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //kollar om email finns i databasen
    boolean existByEmail(String email);

}
