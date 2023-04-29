package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentCreateDto;
import ru.practicum.shareit.item.dto.CommentDto;

public interface CommentService {

  /**
   * createCommentForItem method. Creates comment for item.
   *
   * @param itemId           Id of item.
   * @param sharerId         If of current User.
   * @param commentCreateDto Dto object of Comment to create.
   * @return CommentDto created CommentDto object.
   */
  CommentDto createCommentForItem(Long itemId, Long sharerId, CommentCreateDto commentCreateDto);
}
