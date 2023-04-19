package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @JsonProperty(DtoJsonProperty.ID)
  private Long id;

  @JsonProperty(DtoJsonProperty.NAME)
  private String name;

  @JsonProperty(DtoJsonProperty.EMAIL)
  private String email;

  @JsonProperty(DtoJsonProperty.ITEMS)
  private List<ItemDto> items;

  @JsonProperty(DtoJsonProperty.BOOKING_ID)
  private Long bookingId;

  @JsonProperty(DtoJsonProperty.REQUESTS)
  private List<ItemRequestDto> requests;
}
