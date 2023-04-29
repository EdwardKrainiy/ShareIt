package ru.practicum.shareit.user.utils;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.utils.literal.ExceptionMessage;
import ru.practicum.shareit.utils.literal.LogMessage;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserUtils {

  private final UserRepository userRepository;

  public User getCurrentUserOrException(Long sharerId) {
    return userRepository.findUserById(sharerId)
        .orElseThrow(() -> {
          log.error(String.format(LogMessage.USER_NOT_FOUND_LOG, sharerId));
          throw new EntityNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        });
  }
}
