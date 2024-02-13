package com.electronicbusiness.bidmaster.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(String companyName, LocalDate dateOfEstablishment, String companyId) {
  public UserResponse(String companyName) {
    this(companyName, null, null);
  }
}
