package com.electronicbusiness.bidmaster.service;

import com.electronicbusiness.bidmaster.model.Notification;
import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void create(String message, User user) {
    var notification = new Notification(message, user);
    notificationRepository.save(notification);
  }

  public List<Notification> getAllByUser(User user){
    return notificationRepository.findAllByUser(user);
  }
}
