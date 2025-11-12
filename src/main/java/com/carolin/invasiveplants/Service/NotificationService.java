package com.carolin.invasiveplants.Service;

import com.carolin.invasiveplants.Entity.Notification;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Mapper.NotificationMapper;
import com.carolin.invasiveplants.Repository.NotificationRepository;
import com.carolin.invasiveplants.ResponseDTO.NotificationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationMapper notificationMapper, NotificationRepository notificationRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
    }

    // ################################## NOTIFICATION LIST ##################################################

    //Get all notifications for the current user
    public List<NotificationResponseDTO> getNotificationForUser(User currentUser){

        List<Notification> notifications = notificationRepository.findByUserAndReadFalse(currentUser);

        // mark as read
        // message will only show one time, when the user enter there profile page.
        notifications.forEach(notification -> {
            notification.setRead(true);
        });

        notificationRepository.saveAll(notifications);

        return notifications.stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

}
