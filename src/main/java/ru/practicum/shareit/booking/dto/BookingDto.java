package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.utils.literal.BookingDtoJsonProperty;

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

  @JsonProperty(BookingDtoJsonProperty.ITEM_ID)
  private Long itemId;

  @JsonProperty(BookingDtoJsonProperty.BOOKER_ID)
  private Long bookerId;

  @JsonProperty(BookingDtoJsonProperty.STATUS)
  private String status;
}
