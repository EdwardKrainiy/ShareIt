package ru.practicum.shareit.item.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
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
  public Long createItem(ItemCreateDto itemCreateDto, Long sharerId) {
    User owner = userRepository.findUserById(sharerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, sharerId));
          return new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        });

    Item itemToCreate = ItemMapper.toItem(itemCreateDto);
    itemToCreate.setOwner(owner);

    Long createdItemId = itemRepository.save(itemToCreate).getId();
    log.info(String.format(LogMessage.ITEM_CREATED_LOG, createdItemId));

    return createdItemId;
  }

  @Override
  public ItemDto updateItem(ItemUpdateDto itemUpdateDto, Long itemId, Long sharerId) {
    User owner = userRepository.findUserById(sharerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, sharerId));
          return new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        });

    Item itemToUpdate = itemRepository.findItemByIdAndOwner(itemId, owner)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.ITEM_NOT_FOUND_LOG, itemId));
          return new EntityNotFoundException(ExceptionMessage.ITEM_NOT_FOUND);
        });

    itemToUpdate.setName(itemUpdateDto.getName());
    itemToUpdate.setDescription(itemUpdateDto.getDescription());
    itemToUpdate.setAvailable(itemUpdateDto.isAvailable());

    log.info(String.format(LogMessage.ITEM_UPDATED_LOG, itemId));
    return ItemMapper.toItemDto(itemRepository.save(itemToUpdate));
  }

  @Override
  public ItemDto findItemById(Long itemId) {
    Item foundItem = itemRepository.findItemById(itemId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.ITEM_NOT_FOUND_LOG, itemId));
          return new EntityNotFoundException(ExceptionMessage.ITEM_NOT_FOUND);
        });

    return ItemMapper.toItemDto(foundItem);
  }

  @Override
  public List<ItemDto> findAllItemsByOwnerId(Long ownerId) {
    User owner = userRepository.findUserById(ownerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, ownerId));
          return new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        });

    List<Item> foundItemsByOwner = itemRepository.findItemsByOwner(owner);
    return ItemMapper.toItemDtoList(foundItemsByOwner);
  }

  @Override
  public List<ItemDto> findAllItemsByTextInNameOrDescription(String text) {
    List<Item> foundItemsByTextInNameOrDescription =
        itemRepository.findItemsByAvailable(Boolean.TRUE).stream()
            .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase())
                || item.getDescription().toLowerCase().contains(text.toLowerCase()))
            .collect(Collectors.toList());

    return ItemMapper.toItemDtoList(foundItemsByTextInNameOrDescription);
  }
}
