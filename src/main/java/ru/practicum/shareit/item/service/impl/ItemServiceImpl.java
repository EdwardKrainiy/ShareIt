package ru.practicum.shareit.item.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.utils.mapper.ItemMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final UserRepository userRepository;
  private final ItemRepository itemRepository;

  @Override
  public ItemDto createItem(ItemCreateDto itemCreateDto, Long sharerId) {
    User owner = userRepository.findUserById(sharerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, sharerId));
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.USER_NOT_FOUND);
        });

    Item itemToCreate = ItemMapper.toItem(itemCreateDto);
    itemToCreate.setOwner(owner);

    Item createdItem = itemRepository.save(itemToCreate);
    log.info(String.format(LogMessage.ITEM_CREATED_LOG, createdItem.getId()));

    return ItemMapper.toItemDto(createdItem);
  }

  @Override
  public ItemDto updateItem(ItemUpdateDto itemUpdateDto, Long itemId, Long sharerId) {
    User owner = userRepository.findUserById(sharerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, sharerId));
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.USER_NOT_FOUND);
        });

    Item itemToUpdate = itemRepository.findItemByIdAndOwner(itemId, owner)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.ITEM_NOT_FOUND_LOG, itemId));
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.ITEM_NOT_FOUND);
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
  public ItemDto findItemById(Long itemId) {
    Item foundItem = itemRepository.findItemById(itemId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.ITEM_NOT_FOUND_LOG, itemId));
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.ITEM_NOT_FOUND);
        });

    return ItemMapper.toItemDto(foundItem);
  }

  @Override
  public List<ItemDto> findAllItemsByOwnerId(Long ownerId) {
    User owner = userRepository.findUserById(ownerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, ownerId));
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.USER_NOT_FOUND);
        });

    List<Item> foundItemsByOwner = itemRepository.findItemsByOwner(owner);
    return ItemMapper.toItemDtoList(foundItemsByOwner);
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
