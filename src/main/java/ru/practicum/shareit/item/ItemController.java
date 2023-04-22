package ru.practicum.shareit.item;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.utils.JsonEntitySerializer;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.HttpLiterals;
import ru.practicum.shareit.utils.literal.LogMessage;

@RestController
@RequestMapping(path = "/items")
@Log4j2
@RequiredArgsConstructor
public class ItemController {

  private final JsonEntitySerializer jsonEntitySerializer;

  private final ItemService itemService;

  @Transactional
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<ItemDto> createItem(
      @RequestBody
      @Valid
      @NotNull
      ItemCreateDto itemCreateDto,
      HttpServletRequest request) {
    if (log.isDebugEnabled()) {
      log.debug(String.format(LogMessage.DEBUG_REQUEST_BODY_LOG,
          jsonEntitySerializer.serializeObjectToJson(itemCreateDto)));
    }
    String sharerId = obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(itemService.createItem(itemCreateDto, Long.valueOf(sharerId)));
  }

  @Transactional
  @ResponseStatus(value = HttpStatus.OK)
  @PatchMapping("/{id}")
  public ResponseEntity<ItemDto> updateItem(
      @RequestBody
      @Valid
      @NotNull
      ItemUpdateDto itemUpdateDto,
      @PathVariable("id")
      @NotNull
      Long itemId,
      HttpServletRequest request) {

    if (log.isDebugEnabled()) {
      log.debug(String.format(LogMessage.DEBUG_REQUEST_BODY_LOG,
          jsonEntitySerializer.serializeObjectToJson(itemUpdateDto)));
    }
    String sharerId = obtainAndCheckSharerIdParam(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(itemService.updateItem(itemUpdateDto, itemId, Long.valueOf(sharerId)));
  }

  @ResponseStatus(value = HttpStatus.OK)
  @GetMapping("/{id}")
  public ResponseEntity<ItemDto> getItemById(
      @PathVariable("id")
      @NotNull
      Long itemId) {
    return ResponseEntity.status(HttpStatus.OK).body(itemService.findItemById(itemId));
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<ItemDto>> getAllItemsOfUser(HttpServletRequest request) {
    String sharerId = obtainAndCheckSharerIdParam(request);
    return ResponseEntity.ok(itemService.findAllItemsByOwnerId(Long.valueOf(sharerId)));
  }

  @GetMapping("/search")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<ItemDto>> getAllItemsByTextInNameOrDescription(
      @RequestParam(name = "text")
      String text) {
    return ResponseEntity.ok(itemService.findAllItemsByTextInNameOrDescription(text));
  }

  private String obtainAndCheckSharerIdParam(HttpServletRequest request) {
    String sharerId = request.getHeader(HttpLiterals.SHARER_USER_ID_HEADER_PARAM);

    if (sharerId == null || sharerId.isEmpty()) {
      throw new IllegalArgumentException(ExceptionMessage.SHARER_ID_NOT_FOUND);
    }

    return sharerId;
  }
}
