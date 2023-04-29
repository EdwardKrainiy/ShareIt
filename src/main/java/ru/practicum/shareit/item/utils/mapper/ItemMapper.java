package ru.practicum.shareit.item.utils.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.utils.mapper.BookingMapper;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {

  public static ItemDto toItemDto(Item item) {
    ItemDto itemDto = new ItemDto();

    itemDto.setId(item.getId());
    itemDto.setName(item.getName());
    itemDto.setDescription(item.getDescription());
    itemDto.setAvailable(item.getAvailable());
    itemDto.setComments(
        CollectionUtils.isEmpty(item.getComments())
            ? Collections.emptyList()
            : CommentMapper.toCommentDtoList(item.getComments()));
    return itemDto;
  }

  public static ItemDto toItemDtoWithBookings(Item item, Booking pastBooking,
      Booking futureBooking) {
    ItemDto itemDto = toItemDto(item);

    itemDto.setLastBooking(
        pastBooking == null
            ? null
            : BookingMapper.toBookingForItemDto(pastBooking));
    itemDto.setNextBooking(
        futureBooking == null
            ? null
            : BookingMapper.toBookingForItemDto(futureBooking));

    return itemDto;
  }

  public static Item toItem(ItemCreateDto itemCreateDto) {
    Item item = new Item();

    item.setName(itemCreateDto.getName());
    item.setDescription(itemCreateDto.getDescription());
    item.setAvailable(itemCreateDto.getAvailable());

    return item;
  }

  public static List<ItemDto> toItemDtoList(List<Item> itemList) {
    return itemList.stream()
        .map(ItemMapper::toItemDto)
        .collect(Collectors.toList());
  }
}
