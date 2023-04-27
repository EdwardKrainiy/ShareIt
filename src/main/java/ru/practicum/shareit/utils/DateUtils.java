package ru.practicum.shareit.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  public static String localDateTimeToJsonDate(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

  public static LocalDateTime jsonDateToLocalDateTime(String jsonDate) {
    return LocalDateTime.parse(jsonDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

  public static boolean isDateAfterPresentAndStartDateLessThanEndDate(LocalDateTime startDate,
      LocalDateTime endDate, LocalDateTime nowDate) {
    return !startDate.isAfter(endDate)
        && (startDate.isAfter(nowDate) || startDate.isEqual(nowDate))
        && !startDate.isEqual(endDate);
  }

  public static boolean isCurrentDateInRange(LocalDateTime startDate, LocalDateTime endDate) {
    LocalDateTime currentDate = LocalDateTime.now();
    return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
  }

  public static LocalDateTime getNowLocalDateTime() {
    return LocalDateTime.now();
  }
}
