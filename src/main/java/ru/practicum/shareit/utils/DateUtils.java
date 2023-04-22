package ru.practicum.shareit.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  public static String localDateTimeToJsonDate(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
