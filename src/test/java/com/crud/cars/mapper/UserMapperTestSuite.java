package com.crud.cars.mapper;

import com.crud.cars.domain.User;
import com.crud.cars.domain.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto(10L, "No", "Name", "noname@noname.com");
        //When
        User user = userMapper.mapToUser(userDto);
        //Then
        Assert.assertEquals("Name", user.getLastname());
    }

    @Test
    public void mapToUserDtoTest() {
        //Given
        User user = new User(10L, "John", "Kovalsky", "johnkovalsky@mail.com");
        //When
        UserDto userDto = userMapper.mapToUserDto(user);
        //Then
        Assert.assertEquals("Kovalsky", userDto.getLastname());
    }

    @Test
    public void mapToUserDtoListTest() {
        //Given
        User user = new User(10L, "John", "Kovalsky", "johnkovalsky@mail.com");
        User user2 = new User(11L, "Adam", "Smith", "adamsmith@mail.com");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);
        //Then
        Assert.assertEquals(2, userDtoList.size());
    }
}
