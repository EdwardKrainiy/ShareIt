package ru.practicum.shareit.booking.service;

import java.util.List;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;

public interface BookingService {

  /**
   * createBooking method. Creates booking from JSON object in RequestBody and saves into DB.
   *
   * @param bookingCreateDto Booking transfer object, which we need to save. This one will be
   *                         converted into Booking object, passed some checks and will be saved on
   *                         DB.
   * @param sharerId         Id of user, which creates item.
   * @return BookingDto Created bookingDto object.
   */
  BookingDto createBooking(BookingCreateDto bookingCreateDto, Long sharerId);

  /**
   * updateBookingApprovalStatus method. Updates Booking status.
   *
   * @param bookingId Id of booking we need to update.
   * @param approved  Status, which we need to update.
   * @param ownerId   Item's owner id.
   * @return BookingDto Updated bookingDto object.
   */
  BookingDto updateBookingApprovalStatus(Long bookingId, Boolean approved, Long ownerId);

  /**
   * getBookingByBookingId method. Gets booking by id.
   *
   * @param bookingId Id of requested booking.
   * @param sharerId  Id of current sharer.
   * @return BookingDto Obtained BookingDto object.
   */
  BookingDto getBookingByBookingId(Long bookingId, Long sharerId);

  /**
   * getAllBookingsByState method. Returns all bookings by sharerId and state, which is ALL by
   * default.
   *
   * @param state    State of booking.
   * @param bookerId Id of current booker.
   * @return List<BookingDto> List of obtained BookingDto's.
   */
  List<BookingDto> getAllBookingsByState(String state, Long bookerId);

  /**
   * getAllBookingsByItemsOfCurrentUser method. Returns all bookings of all items by sharerId and
   * state, which is ALL by default.
   *
   * @param state    State of booking.
   * @param bookerId Id of current booker.
   * @return List<BookingDto> List of obtained BookingDto's.
   */
  List<BookingDto> getAllBookingsByItemsOfCurrentUser(String state, Long bookerId);

}
