package ru.practicum.main.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.main.exception.NotFoundException;
import ru.practicum.main.user.model.InputUserDto;
import ru.practicum.main.user.model.UserDto;
import ru.practicum.main.user.model.UserMapper;
import ru.practicum.main.user.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getUsers(Set<Integer> setId, int from, int size) {
        if (setId == null || setId.isEmpty())
            return userRepository.findAll(PageRequest.of(from, size)).getContent()
                    .stream()
                    .map(UserMapper::userToDto)
                    .collect(Collectors.toList());

        return userRepository.findByIdIn(setId, PageRequest.of(from, size))
                .stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
    }

    public UserDto postUser(InputUserDto inputUserDto) {
        return UserMapper.userToDto(userRepository.save(UserMapper.userFromUserCreateRequestDto(inputUserDto)));
    }

    public void deleteUser(Integer id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
            throw new NotFoundException("User not found id = " +  id);
        });
    }

}
