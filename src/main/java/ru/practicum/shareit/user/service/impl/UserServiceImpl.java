package ru.practicum.shareit.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserUpdateDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.utils.mapper.UserMapper;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<UserDto> findAllUsers() {
    return userRepository.findAll().stream().map(UserMapper::toUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto findUserById(Long userId) {
    Optional<User> foundUser = userRepository.findUserById(userId);

    if (foundUser.isPresent()) {
      return UserMapper.toUserDto(foundUser.get());
    } else {
      log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, userId));
      throw new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }
  }

  @Override
  public Long createUser(UserCreateDto userCreateDto) {
    User user = UserMapper.toUser(userCreateDto);

    Long createdUserId = userRepository.save(user).getId();
    log.info(String.format(LogMessage.USER_CREATED_LOG, createdUserId));

    return createdUserId;
  }

  @Override
  public UserDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
    Optional<User> userToUpdateOptional = userRepository.findUserById(userId);
    if (userToUpdateOptional.isPresent()) {
      User userToUpdate = userToUpdateOptional.get();

      userToUpdate.setName(userUpdateDto.getName());
      userToUpdate.setEmail(userUpdateDto.getEmail());

      log.info(String.format(LogMessage.USER_UPDATED_LOG, userId));
      return UserMapper.toUserDto(userRepository.save(userToUpdate));
    } else {
      log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, userId));
      throw new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }
  }

  @Override
  public void deleteUserById(Long userId) {
    Optional<User> userToDeleteOptional = userRepository.findUserById(userId);

    if (userToDeleteOptional.isPresent()) {
      User userToDelete = userToDeleteOptional.get();

      log.info(String.format(LogMessage.USER_DELETED_LOG, userId));
      userRepository.deleteById(userToDelete.getId());
    } else {
      log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, userId));
      throw new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }
  }
}
