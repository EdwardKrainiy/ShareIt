package ru.practicum.shareit.booking;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.utils.HeaderUtils;
import ru.practicum.shareit.utils.JsonEntitySerializer;
import ru.practicum.shareit.utils.literal.State;
import ru.practicum.shareit.utils.literal.LogMessage;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
@Log4j2
@RequiredArgsConstructor
public class BookingController {

  private final JsonEntitySerializer jsonEntitySerializer;

  private final BookingService bookingService;

  @Transactional
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<BookingDto> createBooking(
      @RequestBody
      @Valid
      @NotNull
      BookingCreateDto bookingCreateDto,
      HttpServletRequest request) {
    if (log.isDebugEnabled()) {
      log.debug(String.format(LogMessage.DEBUG_REQUEST_BODY_LOG,
          jsonEntitySerializer.serializeObjectToJson(bookingCreateDto)));
    }
    Long sharerId = HeaderUtils.obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(bookingService.createBooking(bookingCreateDto, sharerId));
  }

  @Transactional
  @PatchMapping("/{bookingId}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<BookingDto> updateBookingApprovalStatus(
      @PathVariable
      @NotNull
      Long bookingId,
      @RequestParam
      @NotNull
      boolean approved,
      HttpServletRequest request) {
    Long ownerId = HeaderUtils.obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(bookingService.updateBookingApprovalStatus(bookingId, approved, ownerId));
  }

  @GetMapping("/{bookingId}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<BookingDto> getBookingByBookingIdAndState(
      @PathVariable
      @NotNull
      Long bookingId,
      HttpServletRequest request) {
    Long sharerId = HeaderUtils.obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(bookingService.getBookingByBookingId(bookingId, sharerId));
  }

  @GetMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<BookingDto>> getBookingByBookingIdAndState(
      @RequestParam(defaultValue = State.ALL_STATE)
      String state,
      HttpServletRequest request) {
    Long bookerId = HeaderUtils.obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(bookingService.getAllBookingsByState(state, bookerId));
  }

  @GetMapping("/owner")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<BookingDto>> getBookingsForAllItemsOfCurrentUser(
      @RequestParam(defaultValue = State.ALL_STATE)
      String state,
      HttpServletRequest request) {
    Long bookerId = HeaderUtils.obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(bookingService.getAllBookingsByItemsOfCurrentUser(state, bookerId));
  }
}
