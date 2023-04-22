package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

  @JsonProperty(DtoJsonProperty.NAME)
  private String name;

  @JsonProperty(DtoJsonProperty.EMAIL)
  @Email
  private String email;
}
