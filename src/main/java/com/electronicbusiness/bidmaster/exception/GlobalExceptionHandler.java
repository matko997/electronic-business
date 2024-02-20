package com.electronicbusiness.bidmaster.exception;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
    var errorResponse = new ErrorResponse(errors);
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Object> handleValidationException(ValidationException validationException) {
    var errorResponse = new ErrorResponse(List.of(validationException.getMessage()));
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneralException(Exception exception) {
    log.error("Exception: {}", exception.getLocalizedMessage());
    return ResponseEntity.internalServerError().body(null);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleGeneralException(
      EntityNotFoundException entityNotFoundException) {
    var errorResponse = new ErrorResponse(List.of(entityNotFoundException.getMessage()));
    return new ResponseEntity<>(errorResponse, NOT_FOUND);
  }
}
