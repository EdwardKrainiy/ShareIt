package ru.practicum.shareit.utils;

import javax.servlet.http.HttpServletRequest;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.HttpLiterals;

public class HeaderUtils {

  public static Long obtainAndCheckSharerIdParam(HttpServletRequest request) {
    String sharerId = request.getHeader(HttpLiterals.SHARER_USER_ID_HEADER_PARAM);

    if (sharerId == null || sharerId.isEmpty()) {
      throw new IllegalArgumentException(ExceptionMessage.SHARER_ID_NOT_FOUND);
    }

    return Long.valueOf(sharerId);
  }
}
