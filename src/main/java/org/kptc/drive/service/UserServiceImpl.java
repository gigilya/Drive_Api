package org.kptc.drive.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.kptc.drive.dto.UserDto;
import org.kptc.drive.entity.User;
import org.kptc.drive.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public boolean auth(UserDto userDto) {
        return userRepository
                .findAll()
                .stream()
                .anyMatch(u -> u.getUsername().equals(userDto.getUsername())
                        && u.getPassword().equals(userDto.getPassword()));
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userDto == null)
            throw new IllegalStateException("No data!");

        String username = userDto.getUsername();

        if (StringUtils.isEmpty(username))
            throw new IllegalStateException("Username can`t be empty!");

        String password = userDto.getPassword();

        if (StringUtils.isEmpty(password))
            throw new IllegalStateException("Password can`t be empty!");

        User toCreate = new User();
        toCreate.setUsername(username);
        toCreate.setPassword(password);

        userRepository.save(toCreate);

        return modelMapper.map(toCreate, UserDto.class);
    }

}
