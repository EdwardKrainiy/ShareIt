package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.utils.literal.ItemDtoJsonProperty;

@Data
@RequiredArgsConstructor
public class ItemUpdateDto {

  @JsonProperty(ItemDtoJsonProperty.NAME)
  private String name;

  @JsonProperty(ItemDtoJsonProperty.DESCRIPTION)
  private String description;

  @JsonProperty(ItemDtoJsonProperty.AVAILABLE)
  private Boolean available;
}
