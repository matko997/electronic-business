package com.electronicbusiness.bidmaster.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class<?> entityClass, String identifier) {
    super(
        String.format(
            "Entity of type %s with identifier '%s' not found",
            entityClass.getSimpleName(), identifier));
  }
}
