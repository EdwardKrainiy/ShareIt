package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.utils.literal.CommentDtoJsonProperty;

@Data
@RequiredArgsConstructor
public class CommentDto {

  @JsonProperty(CommentDtoJsonProperty.ID)
  private Long id;

  @JsonProperty(CommentDtoJsonProperty.TEXT)
  private String text;

  @JsonProperty(CommentDtoJsonProperty.AUTHOR_NAME)
  private String authorName;

  @JsonProperty(CommentDtoJsonProperty.CREATED)
  private String created;
}
