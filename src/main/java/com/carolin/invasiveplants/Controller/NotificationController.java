package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.ResponseDTO.NotificationResponseDTO;
import com.carolin.invasiveplants.Service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/notifications")
public class NotificationController  {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public NotificationController(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    // GET notifications - fetch notifications for the current logged-in user
    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(
            @AuthenticationPrincipal User currentUser){

        if(currentUser== null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<NotificationResponseDTO> notification = notificationService.getNotificationForUser(currentUser);

        return ResponseEntity.ok(notification);
    }

    //User can mark a notification as read so it goes away from the top of page
    @PutMapping("/{id}/read")
    public ResponseEntity<Void>markRead(@PathVariable Long id, @AuthenticationPrincipal User currentUser){

        if(currentUser == null){
            System.out.println("no authenticated user!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        notificationService.markNotificationsAsRead(id,currentUser);
        return ResponseEntity.ok().build();
    }
}
