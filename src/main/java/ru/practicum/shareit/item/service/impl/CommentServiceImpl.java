package ru.practicum.shareit.item.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.item.dto.CommentCreateDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.CommentRepository;
import ru.practicum.shareit.item.service.CommentService;
import ru.practicum.shareit.item.utils.ItemUtils;
import ru.practicum.shareit.item.utils.mapper.CommentMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.utils.UserUtils;
import ru.practicum.shareit.utils.exception.BookingsOfItemByUserNotFound;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final BookingRepository bookingRepository;
  private final UserUtils userUtils;
  private final ItemUtils itemUtils;

  @Override
  public CommentDto createCommentForItem(Long itemId, Long sharerId,
      CommentCreateDto commentCreateDto) {
    List<Booking> bookingsBySharerIdAndItemId = bookingRepository.findBookingsByBooker_IdAndItem_Id(
        sharerId, itemId);

    Item itemToComment = itemUtils.getItemByIdOrException(itemId);
    User currentUser = userUtils.getCurrentUserOrException(sharerId);

    bookingsBySharerIdAndItemId = bookingsBySharerIdAndItemId.stream()
        .filter(booking -> booking.getEndDate().isBefore(LocalDateTime.now()))
        .collect(Collectors.toList());

    if (bookingsBySharerIdAndItemId.isEmpty()) {
      log.error(
          String.format(LogMessage.BOOKINGS_OF_THIS_ITEM_BY_THIS_USER_NOT_FOUND, itemId, sharerId));
      throw new BookingsOfItemByUserNotFound(ExceptionMessage.BOOKINGS_OF_ITEM_NOT_FOUND);
    }

    Comment createdComment = CommentMapper.toComment(commentCreateDto, currentUser, itemToComment);
    createdComment = commentRepository.save(createdComment);
    log.info(String.format(LogMessage.COMMENT_CREATED_LOG, createdComment.getId()));
    return CommentMapper.toCommentDto(createdComment);
  }
}
