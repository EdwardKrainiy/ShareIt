package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.user.utils.literal.UserDtoJsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @JsonProperty(UserDtoJsonProperty.ID)
  private Long id;

  @JsonProperty(UserDtoJsonProperty.NAME)
  private String name;

  @JsonProperty(UserDtoJsonProperty.EMAIL)
  private String email;

  @JsonProperty(UserDtoJsonProperty.ITEMS)
  private List<ItemDto> items;

  @JsonProperty(UserDtoJsonProperty.BOOKING_ID)
  private Long bookingId;

  @JsonProperty(UserDtoJsonProperty.REQUESTS)
  private List<ItemRequestDto> requests;
}
