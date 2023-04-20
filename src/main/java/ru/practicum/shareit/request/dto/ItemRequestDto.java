package ru.practicum.shareit.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.utils.literal.DtoJsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

  @JsonProperty(DtoJsonProperty.ID)
  private Long id;

  @JsonProperty(DtoJsonProperty.DESCRIPTION)
  private String description;

  @JsonProperty(DtoJsonProperty.REQUESTOR_ID)
  private Long requestorId;

  @JsonProperty(DtoJsonProperty.CREATED)
  private String created;

  @JsonProperty(DtoJsonProperty.ITEM_ID)
  private Long itemId;
}
