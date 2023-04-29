package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.utils.literal.BookingDtoJsonProperty;

@Data
@RequiredArgsConstructor
public class BookingForItemDto {

  @JsonProperty(BookingDtoJsonProperty.ID)
  private Long id;
  @JsonProperty(BookingDtoJsonProperty.BOOKER_ID)
  private Long bookerId;
}
