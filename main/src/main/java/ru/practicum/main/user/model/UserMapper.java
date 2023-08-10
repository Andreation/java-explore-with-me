package ru.practicum.main.user.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.main.user.dto.InputUserDto;
import ru.practicum.main.user.dto.UserDto;
import ru.practicum.main.user.dto.UserDtoWithoutEmail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    public static UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User userFromDto(UserDto user) {
        return new User(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User userFromUserCreateRequestDto(InputUserDto inputUserDto) {
        return new User(
                null,
                inputUserDto.getName(),
                inputUserDto.getEmail()
        );
    }

    public static UserDtoWithoutEmail userToShort(User user) {
        return new UserDtoWithoutEmail(
                user.getId(),
                user.getName()
        );
    }

}