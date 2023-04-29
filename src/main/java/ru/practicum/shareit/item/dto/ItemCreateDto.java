package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.utils.literal.ItemDtoJsonProperty;
import ru.practicum.shareit.utils.literal.ValidationExceptionMessage;

@Data
@RequiredArgsConstructor
public class ItemCreateDto {

  @NotBlank(message = ValidationExceptionMessage.NAME_IS_EMPTY)
  @JsonProperty(ItemDtoJsonProperty.NAME)
  private String name;

  @NotBlank(message = ValidationExceptionMessage.DESCRIPTION_IS_EMPTY)
  @JsonProperty(ItemDtoJsonProperty.DESCRIPTION)
  private String description;

  @NotNull(message = ValidationExceptionMessage.AVAILABLE_IS_EMPTY)
  @JsonProperty(ItemDtoJsonProperty.AVAILABLE)
  private Boolean available;
}
