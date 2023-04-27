package ru.practicum.shareit.utils.exception.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.utils.exception.BookingAlreadyApprovedException;
import ru.practicum.shareit.utils.exception.EntityAlreadyExistsException;
import ru.practicum.shareit.utils.exception.UnknownStateException;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(UnknownStateException.class)
  public ResponseEntity<Map<String, String>> handleUnknownStateException(UnknownStateException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Map<String, String>> handleEntityAlreadyExistsException(
      EntityAlreadyExistsException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(BookingAlreadyApprovedException.class)
  public ResponseEntity<Map<String, String>> handleBookingAlreadyApprovedException(
      BookingAlreadyApprovedException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}