package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;
import ru.practicum.shareit.utils.literal.ValidationExceptionMessage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

  @NotBlank(message = ValidationExceptionMessage.NAME_IS_EMPTY)
  @JsonProperty(DtoJsonProperty.NAME)
  private String name;

  @NotBlank(message = ValidationExceptionMessage.EMAIL_IS_EMPTY)
  @JsonProperty(DtoJsonProperty.EMAIL)
  private String email;
}
