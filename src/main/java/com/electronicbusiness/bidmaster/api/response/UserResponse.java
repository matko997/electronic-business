package com.electronicbusiness.bidmaster.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(String companyName, String dateOfEstablishment, String companyId) {
  public UserResponse(String companyName) {
    this(companyName, null, null);
  }
}
