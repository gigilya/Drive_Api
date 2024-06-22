package org.kptc.drive.controller;

import lombok.RequiredArgsConstructor;
import org.kptc.drive.dto.UserDto;
import org.kptc.drive.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("auth")
    public HttpStatus auth(@RequestBody UserDto userDto) {
        return userService.auth(userDto) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        try {
            UserDto result = userService.create(userDto);
            return ResponseEntity.ok(result);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
