package com.crud.cars.controller;

import com.crud.cars.domain.UserDto;
import com.crud.cars.mapper.UserMapper;
import com.crud.cars.service.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/costs")
public class UserController {

    @Autowired
    private DbUser service;

    @Autowired
    private UserMapper mapper;

    @GetMapping(value = "/users")
    public List<UserDto> getUsers() {
        return mapper.mapToUserDtoList(service.getAllUsers());
    }

    @GetMapping(value = "users/{id}")
    public UserDto getUser(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToUserDto(service.getUser(id).orElseThrow(NotFoundException::new));
    }

    @DeleteMapping( value = "users/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteByUserId(id);

    }

    @PutMapping(value = "/users", consumes = APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return mapper.mapToUserDto(service.saveUser(mapper.mapToUser(userDto)));
    }

    @PostMapping(value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(mapper.mapToUser(userDto));
    }
}
