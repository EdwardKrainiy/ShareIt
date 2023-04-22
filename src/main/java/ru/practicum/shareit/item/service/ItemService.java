package ru.practicum.shareit.item.service;

import java.util.List;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;

public interface ItemService {

  /**
   * createItem method. Creates item from JSON object in RequestBody and saves into DB.
   *
   * @param itemCreateDto Item transfer object, which we need to save. This one will be converted
   *                      into Item object, passed some checks and will be saved on DB.
   * @param sharerId      Id of user, which creates item.
   * @return ResponseEntity<Long> ResponseEntity with HTTP code and id of created item.
   */
  ItemDto createItem(ItemCreateDto itemCreateDto, Long sharerId);

  /**
   * updateItem method. Updates item by id and userUpdateDto entity.
   *
   * @param itemUpdateDto Item transfer object, which we need to update. This one will be converted
   *                      into Item object, passed some checks and will be updated on DB.
   * @param itemId        Id of item we need to update.
   * @param sharerId      This item owner's id.
   */
  ItemDto updateItem(ItemUpdateDto itemUpdateDto, Long itemId, Long sharerId);

  /**
   * findItemById. Finds item by id.
   *
   * @param itemId Id of item we need to find.
   * @return ResponseEntity<ItemDto> ResponseEntity with HTTP code and found item entity.
   */
  ItemDto findItemById(Long itemId);

  /**
   * findAllItemsByUserId. Finds all items by owner's id.
   *
   * @param ownerId Id of owner which items we need to get.
   * @return ResponseEntity<List < ItemDto>> ResponseEntity with HTTP code and found items.
   */
  List<ItemDto> findAllItemsByOwnerId(Long ownerId);


  /**
   * findAllItemsByTextInNameOrDescription. Finds all items with given text in description or name.
   *
   * @param text Text to search for items.
   * @return ResponseEntity<List < ItemDto>> ResponseEntity with HTTP code and found items.
   */
  List<ItemDto> findAllItemsByTextInNameOrDescription(String text);
}
