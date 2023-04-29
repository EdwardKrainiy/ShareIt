package ru.practicum.shareit.item.utils;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemUtils {

  private final ItemRepository itemRepository;

  public Item getItemByIdOrException(Long itemId) {

    return itemRepository.findItemById(itemId).orElseThrow(() -> {
      log.error(String.format(LogMessage.ITEM_NOT_FOUND_LOG, itemId));
      throw new EntityNotFoundException(ExceptionMessage.ITEM_NOT_FOUND);
    });
  }
}
