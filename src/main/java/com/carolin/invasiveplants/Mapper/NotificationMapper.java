package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Notification;
import com.carolin.invasiveplants.ResponseDTO.NotificationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponseDTO toDto(Notification notification){
        NotificationResponseDTO dto = new NotificationResponseDTO();

        dto.setId(notification.getNotificationId());
        dto.setMessage(notification.getMessage());
        dto.setNotificationType(notification.getNotificationType());
        dto.setRead(notification.isRead());
        dto.setTime(notification.getTime());

        return dto;
    }

}
