package ru.practicum.shareit.booking.utils.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.utils.DateUtils;

public class BookingMapper {

  public static BookingDto toBookingDto(Booking booking) {
    BookingDto bookingDto = new BookingDto();

    bookingDto.setId(booking.getId());
    bookingDto.setStartDate(DateUtils.localDateTimeToJsonDate(booking.getStartDate()));
    bookingDto.setEndDate(DateUtils.localDateTimeToJsonDate(booking.getEndDate()));
    bookingDto.setItemId(booking.getItem() == null ? null : booking.getItem().getId());
    bookingDto.setBookerId(booking.getBooker() == null ? null : booking.getBooker().getId());
    bookingDto.setStatus(String.valueOf(booking.getStatus()));

    return bookingDto;
  }

  public static List<BookingDto> toBookingDtoList(List<Booking> bookingList) {
    return bookingList.stream()
        .map(BookingMapper::toBookingDto)
        .collect(Collectors.toList());
  }
}
