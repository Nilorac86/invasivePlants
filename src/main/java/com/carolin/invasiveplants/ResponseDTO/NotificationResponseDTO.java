package com.carolin.invasiveplants.ResponseDTO;

import com.carolin.invasiveplants.Enum.NotificationType;

import java.time.LocalDateTime;

public class NotificationResponseDTO {

    private Long id;
    private String message;
    private NotificationType notificationType;
    private LocalDateTime time;
    private boolean read;

    public NotificationResponseDTO() {
    }

    public NotificationResponseDTO(Long id, String message, NotificationType notificationType, LocalDateTime time, boolean read) {
        this.id = id;
        this.message = message;
        this.notificationType = notificationType;
        this.time = time;
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
