package com.carolin.invasiveplants.Entity;

import com.carolin.invasiveplants.Enum.NotificationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notification_id")
    private Long id;

    @Column(name = "message" , length = 225)
    private String message;

    @Column(name = "`read`", nullable = false)
    private boolean read = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType = NotificationType.INFO;

    @Column(name = "time")
    private LocalDateTime time = LocalDateTime.now();

    // Relationship to user that should get notification
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_user", referencedColumnName = "user_id")
    private User user;

    public Notification() {
    }

    public Notification(Long id, String message, boolean read, NotificationType notificationType, LocalDateTime time, User user) {
        this.id = id;
        this.message = message;
        this.read = read;
        this.notificationType = notificationType;
        this.time = time;
        this.user = user;
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

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
