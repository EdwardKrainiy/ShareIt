package ru.practicum.shareit.user;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserUpdateDto;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.utils.JsonEntitySerializer;
import ru.practicum.shareit.utils.literal.LogMessage;

@RestController
@RequestMapping(path = "/users")
@Log4j2
@RequiredArgsConstructor
public class UserController {

  private final JsonEntitySerializer jsonEntitySerializer;

  private final UserService userService;

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<UserDto>> getAllUser() {
    return ResponseEntity.ok(userService.findAllUsers());
  }

  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<UserDto> getUserById(
      @PathVariable("id")
      @NotNull
      Long userId) {
    return ResponseEntity.ok(userService.findUserById(userId));
  }

  @Transactional
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<UserDto> createUser(
      @RequestBody
      @Valid
      @NotNull
      UserCreateDto userCreateDto) {
    if (log.isDebugEnabled()) {
      log.debug(
          String.format(
              LogMessage.DEBUG_REQUEST_BODY_LOG,
              jsonEntitySerializer.serializeObjectToJson(userCreateDto)));
    }

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(userService.createUser(userCreateDto));
  }

  @Transactional
  @ResponseStatus(value = HttpStatus.OK)
  @PatchMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(
      @RequestBody
      @Valid
      @NotNull
      UserUpdateDto userUpdateDto,
      @PathVariable("id") Long userId) {

    if (log.isDebugEnabled()) {
      log.debug(
          String.format(
              LogMessage.DEBUG_REQUEST_BODY_LOG,
              jsonEntitySerializer.serializeObjectToJson(userUpdateDto)));
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.updateUser(userUpdateDto, userId));
  }

  @Transactional
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(
      @PathVariable("id")
      @NotNull
      Long userId) {
    userService.deleteUserById(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
