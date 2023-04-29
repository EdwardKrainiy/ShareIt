package ru.practicum.shareit.item.utils.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import ru.practicum.shareit.item.dto.CommentCreateDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utils.DateUtils;

public class CommentMapper {

  public static CommentDto toCommentDto(Comment comment) {
    CommentDto commentDto = new CommentDto();

    commentDto.setId(comment.getId());
    commentDto.setText(comment.getText());
    commentDto.setAuthorName(comment.getAuthorName());
    commentDto.setCreated(DateUtils.localDateTimeToJsonDate(comment.getCreated()));

    return commentDto;
  }

  public static Comment toComment(CommentCreateDto commentCreateDto, User currentUser,
      Item itemToComment) {
    Comment comment = new Comment();

    comment.setText(commentCreateDto.getText());
    comment.setAuthorName(currentUser == null ? null : currentUser.getName());
    comment.setCreated(LocalDateTime.now());
    comment.setItem(itemToComment);

    return comment;
  }

  public static List<CommentDto> toCommentDtoList(List<Comment> commentList) {
    return commentList.stream()
        .map(CommentMapper::toCommentDto)
        .collect(Collectors.toList());
  }
}
