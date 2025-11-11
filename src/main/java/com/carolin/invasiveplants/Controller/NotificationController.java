package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Repository.UserRepository;
import com.carolin.invasiveplants.ResponseDTO.NotificationResponseDTO;
import com.carolin.invasiveplants.Service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
}
