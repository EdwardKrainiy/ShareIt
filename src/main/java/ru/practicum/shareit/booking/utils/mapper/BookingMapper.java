package ru.practicum.shareit.booking.utils.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingForItemDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.utils.mapper.ItemMapper;
import ru.practicum.shareit.user.utils.mapper.UserMapper;
import ru.practicum.shareit.utils.DateUtils;

public class BookingMapper {

  public static BookingDto toBookingDto(Booking booking) {
    BookingDto bookingDto = new BookingDto();

    bookingDto.setId(booking.getId());
    bookingDto.setStartDate(booking.getStartDate() == null ? null
        : DateUtils.localDateTimeToJsonDate(booking.getStartDate()));
    bookingDto.setEndDate(booking.getEndDate() == null ? null
        : DateUtils.localDateTimeToJsonDate(booking.getEndDate()));
    bookingDto.setItem(
        booking.getItem() == null ? null : ItemMapper.toItemDto(booking.getItem()));
    bookingDto.setBooker(
        booking.getBooker() == null ? null : UserMapper.toUserDto(booking.getBooker()));
    bookingDto.setStatus(String.valueOf(booking.getStatus()));

    return bookingDto;
  }

  public static BookingForItemDto toBookingForItemDto(Booking booking) {
    BookingForItemDto bookingForItemDto = new BookingForItemDto();
    bookingForItemDto.setId(booking.getId());
    bookingForItemDto.setBookerId(booking.getBooker() == null ? null : booking.getBooker().getId());
    return bookingForItemDto;
  }

  public static List<BookingDto> toBookingDtoList(List<Booking> bookingList) {
    return bookingList.stream()
        .map(BookingMapper::toBookingDto)
        .collect(Collectors.toList());
  }
}
