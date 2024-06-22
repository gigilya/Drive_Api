package org.kptc.drive.service;

import org.kptc.drive.dto.UserDto;

import java.util.Collection;

public interface UserService {

    boolean auth(UserDto userDto);
    UserDto create(UserDto userDto);

}
