package ru.practicum.shareit.item.utils.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {

  public static ItemDto toItemDto(Item item) {
    ItemDto itemDto = new ItemDto();

    itemDto.setName(item.getName());
    itemDto.setDescription(item.getDescription());
    itemDto.setAvailable(item.isAvailable());
    itemDto.setOwnerId(item.getOwner() == null ? null : item.getOwner().getId());
    itemDto.setBookingId(item.getBooking() == null ? null : item.getBooking().getId());
    itemDto.setRequestId(item.getRequest() == null ? null : item.getRequest().getId());

    return itemDto;
  }

  public static List<ItemDto> toItemDtoList(List<Item> itemList) {
    return itemList.stream()
        .map(ItemMapper::toItemDto)
        .collect(Collectors.toList());
  }
}
