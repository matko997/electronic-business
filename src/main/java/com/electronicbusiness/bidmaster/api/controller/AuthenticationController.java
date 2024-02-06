package com.electronicbusiness.bidmaster.api.controller;

import com.electronicbusiness.bidmaster.api.request.AuthenticationRequest;
import com.electronicbusiness.bidmaster.api.request.RegisterUserRequest;
import com.electronicbusiness.bidmaster.api.response.AuthenticationResponse;
import com.electronicbusiness.bidmaster.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@RequestBody @Valid RegisterUserRequest request) {
    authenticationService.register(request);
  }

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(@RequestBody @Valid AuthenticationRequest request) {
    return authenticationService.authenticate(request);
  }
}
