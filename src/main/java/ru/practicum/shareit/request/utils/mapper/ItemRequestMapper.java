package ru.practicum.shareit.request.utils.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.utils.mapper.ItemMapper;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

public class ItemRequestMapper {

  public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest) {
    ItemRequestDto itemRequestDto = new ItemRequestDto();

    itemRequestDto.setDescription(itemRequest.getDescription());
    itemRequestDto.setRequestorId(
        itemRequest.getRequestor() == null ? null : itemRequest.getRequestor().getId());
    itemRequestDto.setItemId(
        itemRequest.getItem() == null ? null : itemRequest.getItem().getId());

    return itemRequestDto;
  }

  public static List<ItemRequestDto> toItemRequestDtoList(List<ItemRequest> itemRequestList) {
    return itemRequestList.stream()
        .map(ItemRequestMapper::toItemRequestDto)
        .collect(Collectors.toList());
  }
}
