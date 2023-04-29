package ru.practicum.shareit.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingsOfItemByUserNotFound extends RuntimeException{
  public BookingsOfItemByUserNotFound(String message){
    super(message);
  }

}
