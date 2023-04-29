package ru.practicum.shareit.item.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.utils.BookingUtils;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.utils.ItemUtils;
import ru.practicum.shareit.item.utils.mapper.ItemMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.utils.UserUtils;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final UserUtils userUtils;
  private final ItemUtils itemUtils;
  private final ItemRepository itemRepository;
  private final BookingRepository bookingRepository;

  @Override
  public ItemDto createItem(ItemCreateDto itemCreateDto, Long sharerId) {
    User owner = userUtils.getCurrentUserOrException(sharerId);

    Item itemToCreate = ItemMapper.toItem(itemCreateDto);
    itemToCreate.setOwner(owner);

    Item createdItem = itemRepository.save(itemToCreate);
    log.info(String.format(LogMessage.ITEM_CREATED_LOG, createdItem.getId()));

    return ItemMapper.toItemDto(createdItem);
  }

  @Override
  public ItemDto updateItem(ItemUpdateDto itemUpdateDto, Long itemId, Long sharerId) {
    User owner = userUtils.getCurrentUserOrException(sharerId);

    Item itemToUpdate = itemRepository.findItemByIdAndOwner(itemId, owner)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.ITEM_NOT_FOUND_LOG, itemId));
          throw new EntityNotFoundException(ExceptionMessage.ITEM_NOT_FOUND);
        });

    itemToUpdate.setName(
        itemUpdateDto.getName() == null
            ? itemToUpdate.getName()
            : itemUpdateDto.getName());
    itemToUpdate.setDescription(
        itemUpdateDto.getDescription() == null
            ? itemToUpdate.getDescription()
            : itemUpdateDto.getDescription());
    itemToUpdate.setAvailable(
        itemUpdateDto.getAvailable() == null
            ? itemToUpdate.getAvailable()
            : itemUpdateDto.getAvailable());

    log.info(String.format(LogMessage.ITEM_UPDATED_LOG, itemId));
    return ItemMapper.toItemDto(itemRepository.save(itemToUpdate));
  }

  @Override
  public ItemDto findItemById(Long itemId, Long userId) {
    Item foundItem = itemUtils.getItemByIdOrException(itemId);

    User owner = foundItem.getOwner();
    if (owner == null) {
      log.error(String.format(LogMessage.OWNER_OF_ITEM_NOT_FOUND, itemId));
      throw new EntityNotFoundException(ExceptionMessage.OWNER_OF_ITEM_NOT_FOUND);
    }

    if (owner.getId().equals(userId)) {
      return getItemsWithNearestBookings(foundItem);
    }

    return ItemMapper.toItemDto(foundItem);
  }

  @Override
  public List<ItemDto> findAllItemsByOwnerId(Long ownerId) {
    User owner = userUtils.getCurrentUserOrException(ownerId);

    List<Item> foundItemsByOwner = itemRepository.findItemsByOwnerOrderById(owner);

    return foundItemsByOwner.stream().map(this::getItemsWithNearestBookings)
        .collect(Collectors.toList());
  }

  private ItemDto getItemsWithNearestBookings(Item foundItem) {
    List<Booking> bookingsByItem = bookingRepository.findBookingsByItem_Id(foundItem.getId());

    Optional<Booking> nearestFutureBooking = BookingUtils.findNearestFutureBooking(
        bookingsByItem, LocalDateTime.now());
    Optional<Booking> nearestPastBooking = BookingUtils.findNearestPastBooking(bookingsByItem,
        LocalDateTime.now());

    return ItemMapper.toItemDtoWithBookings(foundItem, nearestPastBooking.orElse(null),
        nearestFutureBooking.orElse(null));
  }

  @Override
  public List<ItemDto> findAllItemsByTextInNameOrDescription(String text) {
    if (text == null || text.isEmpty()) {
      return Collections.emptyList();
    }

    List<Item> foundItemsByTextInNameOrDescription =
        itemRepository.findItemsByAvailable(Boolean.TRUE).stream()
            .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase())
                || item.getDescription().toLowerCase().contains(text.toLowerCase()))
            .collect(Collectors.toList());

    return ItemMapper.toItemDtoList(foundItemsByTextInNameOrDescription);
  }
}
