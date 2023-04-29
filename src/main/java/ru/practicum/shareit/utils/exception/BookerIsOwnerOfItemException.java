package ru.practicum.shareit.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookerIsOwnerOfItemException extends RuntimeException {

  public BookerIsOwnerOfItemException(String message) {
    super(message);
  }
}
