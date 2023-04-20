package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

  @JsonProperty(DtoJsonProperty.ID)
  private Long id;

  @JsonProperty(DtoJsonProperty.START)
  private String startDate;

  @JsonProperty(DtoJsonProperty.END)
  private String endDate;

  @JsonProperty(DtoJsonProperty.ITEM_ID)
  private Long itemId;

  @JsonProperty(DtoJsonProperty.BOOKER_ID)
  private Long bookerId;

  @JsonProperty(DtoJsonProperty.STATUS)
  private String status;
}
