package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;
import ru.practicum.shareit.utils.literal.ValidationExceptionMessage;

@Data
@NoArgsConstructor
public class ItemCreateDto {

  @NotBlank(message = ValidationExceptionMessage.NAME_IS_EMPTY)
  @JsonProperty(DtoJsonProperty.NAME)
  private String name;

  @NotBlank(message = ValidationExceptionMessage.DESCRIPTION_IS_EMPTY)
  @JsonProperty(DtoJsonProperty.DESCRIPTION)
  private String description;

  @NotNull(message = ValidationExceptionMessage.AVAILABLE_IS_EMPTY)
  @JsonProperty(DtoJsonProperty.AVAILABLE)
  private Boolean available;
}
