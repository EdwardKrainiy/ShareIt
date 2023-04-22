package ru.practicum.shareit.user.service;

import java.util.List;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserUpdateDto;

public interface UserService {

  /**
   * findAllUsers method. Finds all users from DB.
   *
   * @return ResponseEntity<List < UserDto>> ResponseEntity with HTTP code and list of all found
   * users.
   */
  List<UserDto> findAllUsers();

  /**
   * findUserById. Finds user by id.
   *
   * @param userId Id of user we need to find.
   * @return ResponseEntity<UserDto> ResponseEntity with HTTP code and found user entity.
   */
  UserDto findUserById(Long userId);

  /**
   * createUser method. Creates user from JSON object in RequestBody and saves into DB.
   *
   * @param userCreateDto User transfer object, which we need to save. This one will be converted
   *                      into User object, passed some checks and will be saved on DB.
   * @return ResponseEntity<Long> ResponseEntity with HTTP code and id of created user.
   */
  Long createUser(UserCreateDto userCreateDto);

  /**
   * updateUser method. Updates user by id and userUpdateDto entity.
   *
   * @param userUpdateDto User transfer object, which we need to update. This one will be converted
   *                      into User object, passed some checks and will be updated on DB.
   * @param userId        Id of user we need to update.
   */
  UserDto updateUser(UserUpdateDto userUpdateDto, Long userId);

  /**
   * deleteUserById method. Deletes user by id.
   *
   * @param userId Id of user we need to delete.
   */
  void deleteUserById(Long userId);
}
