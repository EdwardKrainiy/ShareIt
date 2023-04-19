package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;

/**
 * TODO Sprint add-controllers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

  @JsonProperty(DtoJsonProperty.ID)
  private Long id;

  @JsonProperty(DtoJsonProperty.NAME)
  private String name;

  @JsonProperty(DtoJsonProperty.DESCRIPTION)
  private String description;

  @JsonProperty(DtoJsonProperty.AVAILABLE)
  private boolean available;

  @JsonProperty(DtoJsonProperty.OWNER_ID)
  private Long ownerId;

  @JsonProperty(DtoJsonProperty.REQUEST_ID)
  private Long requestId;

  @JsonProperty(DtoJsonProperty.BOOKING_ID)
  private Long bookingId;
}
