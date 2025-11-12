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

    //Get all notifications for the current user
    public List<NotificationResponseDTO> getNotificationForUser(User currentUser){

        List<Notification> notifications = notificationRepository.findByUserAndReadFalse(currentUser);

        // DO NOT mark read
        return notifications.stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    // Mark a notification as read by the user
    public void markNotificationsAsRead(Long notificationId, User currentUser){

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(()-> new RuntimeException("Notification not found"));

        if(!notification.getUser().getUserId().equals(currentUser.getUserId())){
            throw new RuntimeException("Unautorized");
        }

        notification.setRead(true);
        notificationRepository.save(notification);
    }

}
