package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.user.utils.literal.UserDtoJsonProperty;

@Data
@RequiredArgsConstructor
public class UserUpdateDto {

  @JsonProperty(UserDtoJsonProperty.NAME)
  private String name;

  @JsonProperty(UserDtoJsonProperty.EMAIL)
  @Email
  private String email;
}
