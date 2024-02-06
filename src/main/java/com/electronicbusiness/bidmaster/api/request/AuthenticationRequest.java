package com.electronicbusiness.bidmaster.api.request;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
    @NotBlank(message = "Company name must be provided") String companyName,
    @NotBlank(message = "Password must be provided") String password) {}
