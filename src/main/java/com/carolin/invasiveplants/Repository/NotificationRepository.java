package com.carolin.invasiveplants.Repository;


import com.carolin.invasiveplants.Entity.Notification;
import com.carolin.invasiveplants.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUser(User user);

    @Query("SELECT n FROM Notification n WHERE n.user.userId = :userId")
    List<Notification> findByUserId(@Param("userId") Long userId);

    List<Notification> findByUserAndReadFalse(User user);

}
