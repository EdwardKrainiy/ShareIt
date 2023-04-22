package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;

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
  private Boolean available;
}
