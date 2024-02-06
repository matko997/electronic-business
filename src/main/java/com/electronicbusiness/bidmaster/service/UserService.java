package com.electronicbusiness.bidmaster.service;

import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public void save(User user) {
    try {
      userRepository.save(user);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to save user to db " + user + ". Message: " + e.getMessage());
    }
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
