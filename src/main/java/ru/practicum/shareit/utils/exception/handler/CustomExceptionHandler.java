package ru.practicum.shareit.utils.exception.handler;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.utils.exception.BookerIsOwnerOfItemException;
import ru.practicum.shareit.utils.exception.BookingAlreadyApprovedException;
import ru.practicum.shareit.utils.exception.BookingsOfItemByUserNotFound;
import ru.practicum.shareit.utils.exception.EntityAlreadyExistsException;
import ru.practicum.shareit.utils.exception.ItemInBookingNotFoundException;
import ru.practicum.shareit.utils.exception.ItemNotAvailableException;
import ru.practicum.shareit.utils.exception.UnknownStateException;
import ru.practicum.shareit.utils.exception.UserNotOwnerOfItemException;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(UnknownStateException.class)
  public ResponseEntity<Map<String, String>> handleUnknownStateException(UnknownStateException ex) {
    Map<String, String> response = getResponseMap(ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Map<String, String>> handleEntityAlreadyExistsException(
      EntityAlreadyExistsException ex) {
    Map<String, String> response = getResponseMap(ex);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(BookingAlreadyApprovedException.class)
  public ResponseEntity<Map<String, String>> handleBookingAlreadyApprovedException(
      BookingAlreadyApprovedException ex) {
    Map<String, String> response = getResponseMap(ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(BookerIsOwnerOfItemException.class)
  public ResponseEntity<Map<String, String>> handleBookerIsOwnerOfItemException(
      BookerIsOwnerOfItemException ex) {
    Map<String, String> response = getResponseMap(ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleEntityNotFoundException(
      EntityNotFoundException ex) {
    Map<String, String> response = getResponseMap(ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(ItemNotAvailableException.class)
  public ResponseEntity<Map<String, String>> handleItemNotAvailableException(
      ItemNotAvailableException ex) {
    Map<String, String> response = getResponseMap(ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(ItemInBookingNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleItemInBookingNotFoundException(
      ItemInBookingNotFoundException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(UserNotOwnerOfItemException.class)
  public ResponseEntity<Map<String, String>> handleUserNotOwnerOfItemException(
      UserNotOwnerOfItemException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(BookingsOfItemByUserNotFound.class)
  public ResponseEntity<Map<String, String>> handleBookingsOfItemByUserNotFound(
      BookingsOfItemByUserNotFound ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  private static Map<String, String> getResponseMap(RuntimeException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    response.put("error", ex.getMessage());
    return response;
  }
}