package com.electronicbusiness.bidmaster.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record RegisterUserRequest(
    @NotBlank(message = "Company name must be provided") String companyName,
    @NotBlank(message = "Password must be provided") String password,
    @NotBlank(message = "Company ID must be provided") String companyId,
    @NotNull(message = "Date of establishment must be provided")
        @Past(message = "Date of establishment must be in the past")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfEstablishment) {}
