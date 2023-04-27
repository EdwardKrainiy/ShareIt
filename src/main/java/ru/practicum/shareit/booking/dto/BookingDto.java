package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.utils.literal.BookingDtoJsonProperty;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

  @JsonProperty(BookingDtoJsonProperty.ID)
  private Long id;

  @JsonProperty(BookingDtoJsonProperty.START)
  private String startDate;

  @JsonProperty(BookingDtoJsonProperty.END)
  private String endDate;

  @JsonProperty(BookingDtoJsonProperty.ITEM)
  private ItemDto item;

  @JsonProperty(BookingDtoJsonProperty.BOOKER)
  private UserDto booker;

  @JsonProperty(BookingDtoJsonProperty.STATUS)
  private String status;
}
