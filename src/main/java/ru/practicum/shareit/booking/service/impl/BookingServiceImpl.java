package ru.practicum.shareit.booking.service.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.utils.mapper.BookingMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.utils.ItemUtils;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.utils.UserUtils;
import ru.practicum.shareit.utils.DateUtils;
import ru.practicum.shareit.utils.enums.Status;
import ru.practicum.shareit.utils.exception.BookerIsOwnerOfItemException;
import ru.practicum.shareit.utils.exception.BookingAlreadyApprovedException;
import ru.practicum.shareit.utils.exception.ItemInBookingNotFoundException;
import ru.practicum.shareit.utils.exception.ItemNotAvailableException;
import ru.practicum.shareit.utils.exception.UnknownStateException;
import ru.practicum.shareit.utils.exception.UserNotOwnerOfItemException;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;
import ru.practicum.shareit.utils.literal.State;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final UserUtils userUtils;
  private final ItemUtils itemUtils;
  private final BookingRepository bookingRepository;

  @Override
  public BookingDto createBooking(BookingCreateDto bookingCreateDto, Long sharerId) {
    User booker = userUtils.getCurrentUserOrException(sharerId);

    Item itemForBooking = itemUtils.getItemByIdOrException(bookingCreateDto.getItemId());

    if (booker.getItems().contains(itemForBooking)) {
      log.error(String.format(LogMessage.BOOKER_IS_AN_OWNER_OF_ITEM, booker.getId(),
          itemForBooking.getId()));
      throw new BookerIsOwnerOfItemException(ExceptionMessage.BOOKER_IS_OWNER_OF_ITEM);
    }

    if (Boolean.FALSE.equals(itemForBooking.getAvailable())) {
      log.error(String.format(LogMessage.ITEM_IS_NOT_AVAILABLE_LOG, bookingCreateDto.getItemId()));
      throw new ItemNotAvailableException(ExceptionMessage.ITEM_NOT_AVAILABLE);
    }

    Booking bookingToSave = createAndFillBookingToSave(bookingCreateDto, itemForBooking, booker);
    Booking createdBooking = bookingRepository.save(bookingToSave);
    log.info(String.format(LogMessage.BOOKING_CREATED_LOG, createdBooking.getId()));

    return BookingMapper.toBookingDto(createdBooking);
  }

  @Override
  public BookingDto updateBookingApprovalStatus(Long bookingId, Boolean approved, Long ownerId) {
    Booking foundBooking = bookingRepository.findBookingById(bookingId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.BOOKING_NOT_FOUND_LOG, bookingId));
          throw new EntityNotFoundException(ExceptionMessage.BOOKING_NOT_FOUND);
        });

    if (foundBooking.getStatus() == Status.APPROVED) {
      log.error(
          String.format(LogMessage.BOOKING_ALREADY_APPROVED, bookingId));
      throw new BookingAlreadyApprovedException(ExceptionMessage.BOOKING_ALREADY_APPROVED);
    }

    Item itemInBooking = foundBooking.getItem();
    if (itemInBooking == null) {
      throw new ItemInBookingNotFoundException(ExceptionMessage.ITEM_NOT_FOUND);
    }

    User owner = userUtils.getCurrentUserOrException(ownerId);

    if (itemInBooking.getOwner() == null) {
      throw new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }

    if (!itemInBooking.getOwner().equals(owner)) {
      log.error(
          String.format(LogMessage.USER_NOT_OWNER_OF_BOOKING_ITEM, ownerId, itemInBooking.getId(),
              bookingId));
      throw new UserNotOwnerOfItemException(ExceptionMessage.USER_NOT_OWNER_OF_BOOKING_ITEM);
    }

    foundBooking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
    foundBooking = bookingRepository.save(foundBooking);

    return BookingMapper.toBookingDto(foundBooking);
  }

  @Override
  public BookingDto getBookingByBookingId(Long bookingId, Long sharerId) {
    Booking foundBooking = bookingRepository.findBookingByIdAndItemOwnerIdOrBookerId(bookingId,
            sharerId, sharerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.BOOKING_NOT_FOUND_LOG, bookingId));
          throw new EntityNotFoundException(ExceptionMessage.BOOKING_NOT_FOUND);
        });

    return BookingMapper.toBookingDto(foundBooking);
  }

  @Override
  public List<BookingDto> getAllBookingsByState(String state, Long bookerId) {
    User booker = userUtils.getCurrentUserOrException(bookerId);

    List<Booking> foundBookingDtosBySharer = bookingRepository.findBookingsByBooker(booker);

    foundBookingDtosBySharer = filterBookingsByState(state, foundBookingDtosBySharer,
        DateUtils.getNowLocalDateTime())
        .stream()
        .sorted(Comparator.comparing(Booking::getStartDate).reversed())
        .collect(Collectors.toList());

    return BookingMapper.toBookingDtoList(foundBookingDtosBySharer);
  }

  @Override
  public List<BookingDto> getAllBookingsByItemsOfCurrentUser(String state, Long bookerId) {
    User booker = userUtils.getCurrentUserOrException(bookerId);

    List<Booking> allBookingsForAllItemsOfCurrentUser = bookingRepository.findAllBookingsForCurrentUser(
        booker.getId());

    allBookingsForAllItemsOfCurrentUser = filterBookingsByState(state,
        allBookingsForAllItemsOfCurrentUser, DateUtils.getNowLocalDateTime())
        .stream()
        .sorted(Comparator.comparing(Booking::getStartDate).reversed())
        .collect(Collectors.toList());

    return BookingMapper.toBookingDtoList(allBookingsForAllItemsOfCurrentUser);
  }

  private static List<Booking> filterBookingsByState(String state,
      List<Booking> foundBookingDtosBySharer, LocalDateTime now) {
    switch (state) {
      case State.CURRENT_STATE:
        return foundBookingDtosBySharer.stream()
            .filter(booking -> DateUtils.isCurrentDateInRange(booking.getStartDate(),
                booking.getEndDate()))
            .collect(Collectors.toList());
      case State.FUTURE_STATE:
        return foundBookingDtosBySharer.stream()
            .filter(booking -> booking.getStartDate().isAfter(now))
            .collect(Collectors.toList());
      case State.PAST_STATE:
        return foundBookingDtosBySharer.stream()
            .filter(booking -> booking.getEndDate().isBefore(now))
            .collect(Collectors.toList());
      case State.REJECTED_STATE:
        return foundBookingDtosBySharer.stream()
            .filter(booking -> booking.getStatus().equals(Status.REJECTED))
            .collect(Collectors.toList());
      case State.WAITING_STATE:
        return foundBookingDtosBySharer.stream()
            .filter(booking -> booking.getStatus().equals(Status.WAITING))
            .collect(Collectors.toList());
      case State.ALL_STATE:
        return foundBookingDtosBySharer;
      default:
        log.error(String.format(LogMessage.UNKNOWN_STATE_LOG, state));
        throw new UnknownStateException(String.format(ExceptionMessage.UNKNOWN_STATE, state));
    }
  }

  private Booking createAndFillBookingToSave(BookingCreateDto bookingCreateDto, Item itemForBooking,
      User booker) {
    LocalDateTime startDate = DateUtils.jsonDateToLocalDateTime(bookingCreateDto.getStartDate());
    LocalDateTime endDate = DateUtils.jsonDateToLocalDateTime(bookingCreateDto.getEndDate());

    checkValidityOfDates(bookingCreateDto, startDate, endDate);

    Booking booking = new Booking();
    booking.setStartDate(startDate);
    booking.setEndDate(endDate);
    booking.setItem(itemForBooking);
    booking.setBooker(booker);
    booking.setStatus(Status.WAITING);
    return booking;
  }

  private static void checkValidityOfDates(BookingCreateDto bookingCreateDto,
      LocalDateTime startDate,
      LocalDateTime endDate) {
    if (!DateUtils.isDateAfterPresentAndStartDateLessThanEndDate(startDate, endDate)) {
      log.error(String.format(LogMessage.INCORRECT_DATES, bookingCreateDto.getStartDate(),
          bookingCreateDto.getEndDate()));
      throw new ResponseStatusException(BAD_REQUEST, ExceptionMessage.INCORRECT_DATES);
    }
  }
}