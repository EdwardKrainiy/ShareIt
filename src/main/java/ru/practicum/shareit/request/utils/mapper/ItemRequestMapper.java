package ru.practicum.shareit.request.utils.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.utils.DateUtils;

public class ItemRequestMapper {

  public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest) {
    ItemRequestDto itemRequestDto = new ItemRequestDto();

    itemRequestDto.setId(itemRequest.getId());
    itemRequestDto.setDescription(itemRequest.getDescription());
    itemRequestDto.setRequestorId(
        itemRequest.getRequestor() == null ? null : itemRequest.getRequestor().getId());
    itemRequestDto.setItemId(
        itemRequest.getItem() == null ? null : itemRequest.getItem().getId());
    itemRequestDto.setCreated(DateUtils.localDateTimeToJsonDate(itemRequest.getCreated()));

    return itemRequestDto;
  }

  public static List<ItemRequestDto> toItemRequestDtoList(List<ItemRequest> itemRequestList) {
    return itemRequestList.stream()
        .map(ItemRequestMapper::toItemRequestDto)
        .collect(Collectors.toList());
  }
}
