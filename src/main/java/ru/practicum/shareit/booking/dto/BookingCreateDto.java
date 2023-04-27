package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.utils.literal.BookingDtoJsonProperty;
import ru.practicum.shareit.utils.literal.ValidationExceptionMessage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateDto {

  @NotNull(message = ValidationExceptionMessage.ITEM_ID_IS_EMPTY)
  @JsonProperty(BookingDtoJsonProperty.ITEM_ID)
  private Long itemId;

  @NotBlank(message = ValidationExceptionMessage.START_DATE_IS_EMPTY)
  @JsonProperty(BookingDtoJsonProperty.START)
  private String startDate;

  @NotNull(message = ValidationExceptionMessage.END_DATE_IS_EMPTY)
  @JsonProperty(BookingDtoJsonProperty.END)
  private String endDate;
}
