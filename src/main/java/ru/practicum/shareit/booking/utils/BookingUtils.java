package ru.practicum.shareit.booking.utils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.utils.enums.Status;

public class BookingUtils {

  public static Optional<Booking> findNearestFutureBooking(List<Booking> bookings,
      LocalDateTime now) {
    Optional<Booking> nextBookingOpt = bookings.stream()
        .filter(b -> b.getStartDate().isAfter(now)
            && b.getStatus().equals(Status.APPROVED))
        .min(Comparator.comparing(Booking::getStartDate));
    if (nextBookingOpt.isEmpty()) {
      return Optional.empty();
    } else {
      return nextBookingOpt;
    }
  }

  public static Optional<Booking> findNearestPastBooking(List<Booking> bookings,
      LocalDateTime now) {
    Optional<Booking> pastBookingOpt = bookings.stream()
        .filter(b -> b.getStartDate().isBefore(now)
            && b.getStatus().equals(Status.APPROVED))
        .max(Comparator.comparing(Booking::getEndDate));
    if (pastBookingOpt.isEmpty()) {
      return Optional.empty();
    } else {
      return pastBookingOpt;
    }
  }
}
