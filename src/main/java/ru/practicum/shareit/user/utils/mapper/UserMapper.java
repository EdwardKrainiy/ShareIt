package ru.practicum.shareit.user.utils.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;
import ru.practicum.shareit.item.utils.mapper.ItemMapper;
import ru.practicum.shareit.request.utils.mapper.ItemRequestMapper;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

public class UserMapper {

  public static UserDto toUserDto(User user) {
    UserDto userDto = new UserDto();

    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setItems(
        CollectionUtils.isEmpty(user.getItems()) ? Collections.emptyList()
            : ItemMapper.toItemDtoList(user.getItems()));
    userDto.setRequests(
        CollectionUtils.isEmpty(user.getItems()) ? Collections.emptyList()
            : ItemRequestMapper.toItemRequestDtoList(user.getRequests()));
    userDto.setBookingId(user.getBooking() == null ? null : user.getBooking().getId());

    return userDto;
  }

  public static User toUser(UserCreateDto userCreateDto) {
    User user = new User();

    user.setName(userCreateDto.getName());
    user.setEmail(userCreateDto.getEmail());

    return user;
  }

  public static List<UserDto> toUserDtoList(List<User> userList) {
    return userList.stream()
        .map(UserMapper::toUserDto)
        .collect(Collectors.toList());
  }
}
