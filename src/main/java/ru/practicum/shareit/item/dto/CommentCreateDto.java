package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.utils.literal.CommentDtoJsonProperty;
import ru.practicum.shareit.utils.literal.ValidationExceptionMessage;

@Data
@RequiredArgsConstructor
public class CommentCreateDto {

  @NotBlank(message = ValidationExceptionMessage.TEXT_IS_EMPTY)
  @JsonProperty(CommentDtoJsonProperty.TEXT)
  private String text;
}
