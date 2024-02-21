package com.electronicbusiness.bidmaster.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.electronicbusiness.bidmaster.api.request.NotificationRequest;
import com.electronicbusiness.bidmaster.api.response.NotificationResponse;
import com.electronicbusiness.bidmaster.api.service.NotificationPresentationService;
import com.electronicbusiness.bidmaster.exception.EntityNotFoundException;
import com.electronicbusiness.bidmaster.model.Notification;
import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.security.JwtService;
import com.electronicbusiness.bidmaster.service.NotificationService;
import com.electronicbusiness.bidmaster.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

  private final NotificationService notificationService;
  private final JwtService jwtService;
  private final UserService userService;
  private final NotificationPresentationService notificationPresentationService;

  @PostMapping
  @ResponseStatus(CREATED)
  public void createNotification(
      @RequestHeader("Authorization") String authHeader,
      @RequestBody NotificationRequest notificationRequest) {
    String username = jwtService.extractUsername(authHeader.substring(7));
    User user =
        userService
            .findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException(User.class, username));
    notificationService.create(notificationRequest.message(), user);
  }

  @GetMapping
  public List<NotificationResponse> getNotifications(
      @RequestHeader("Authorization") String authHeader) {
    String username = jwtService.extractUsername(authHeader.substring(7));
    User user =
        userService
            .findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException(User.class, username));
    List<Notification> userNotifications = notificationService.getAllByUser(user);
    return notificationPresentationService.buildList(userNotifications);
  }
}
