package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.dto.BookingForItemDto;
import ru.practicum.shareit.item.utils.literal.ItemDtoJsonProperty;

@Data
@RequiredArgsConstructor
public class ItemDto {

  @JsonProperty(ItemDtoJsonProperty.ID)
  private Long id;

  @JsonProperty(ItemDtoJsonProperty.NAME)
  private String name;

  @JsonProperty(ItemDtoJsonProperty.DESCRIPTION)
  private String description;

  @JsonProperty(ItemDtoJsonProperty.AVAILABLE)
  private Boolean available;

  @JsonProperty(ItemDtoJsonProperty.LAST_BOOKING)
  private BookingForItemDto lastBooking;

  @JsonProperty(ItemDtoJsonProperty.NEXT_BOOKING)
  private BookingForItemDto nextBooking;

  @JsonProperty(ItemDtoJsonProperty.COMMENTS)
  private List<CommentDto> comments;
}
