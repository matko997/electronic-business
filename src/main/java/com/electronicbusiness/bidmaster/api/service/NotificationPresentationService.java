package com.electronicbusiness.bidmaster.api.service;

import com.electronicbusiness.bidmaster.api.response.NotificationResponse;
import com.electronicbusiness.bidmaster.model.Notification;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificationPresentationService {

  public List<NotificationResponse> buildList(List<Notification> notifications) {
    return notifications.stream().map(this::buildSingle).toList();
  }

  private NotificationResponse buildSingle(Notification notification) {
    return new NotificationResponse(
        notification.getId(), notification.getCreatedAt().toString(), notification.getMessage());
  }
}
