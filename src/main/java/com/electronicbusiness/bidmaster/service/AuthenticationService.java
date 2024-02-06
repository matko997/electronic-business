package com.electronicbusiness.bidmaster.service;

import static com.electronicbusiness.bidmaster.model.enumeration.Role.*;

import com.electronicbusiness.bidmaster.api.request.AuthenticationRequest;
import com.electronicbusiness.bidmaster.api.request.RegisterUserRequest;
import com.electronicbusiness.bidmaster.api.response.AuthenticationResponse;
import com.electronicbusiness.bidmaster.model.SecurityUser;
import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.security.JwtService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public void register(RegisterUserRequest request) {
    Optional<User> optionalUser = userService.findByUsername(request.companyName());
    if (optionalUser.isPresent()) {
      throw new RuntimeException("User with " + request.companyName() + " already exists");
    }
    var user =
        User.builder()
            .username(request.companyName())
            .dateOfEstablishment(request.dateOfEstablishment())
            .password(passwordEncoder.encode(request.password()))
            .companyId(request.companyId())
            .role(USER)
            .build();
    userService.save(user);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.companyName(), request.password()));
    var user = userService.findByUsername(request.companyName()).orElseThrow();
    var jwtToken = jwtService.generateToken(new SecurityUser(user));
    return new AuthenticationResponse(jwtToken);
  }
}
