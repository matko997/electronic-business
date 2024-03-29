package com.electronicbusiness.bidmaster.repository;


import com.electronicbusiness.bidmaster.model.Notification;
import com.electronicbusiness.bidmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUser(User user);
}
