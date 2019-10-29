package com.crud.cars.service;

import com.crud.cars.domain.User;
import com.crud.cars.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbUserTestSuite {

    @Autowired
    private DbUser dbUser;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getAllUsersTest(){
        //Given
        User user = new User(10L, "John", "Kovalsky", "johnkovalsky@mail.com");
        User user2 = new User(11L, "Adam", "Smith", "adamsmith@mail.com");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        //When
        List<User> resultList = dbUser.getAllUsers();
        //Then
        assertEquals(2,resultList.size());
    }
    @Test
    public void getUserTest(){
        //Given
        User user = new User(10L, "John", "Kovalsky", "johnkovalsky@mail.com");
        when(userRepository.findById(10L)).thenReturn(Optional.ofNullable(user));
        //When
        Optional<User> result = dbUser.getUser(10L);
        //Then
        assertEquals("Kovalsky",result.get().getLastname());
    }
    @Test
    public void saveUserTest(){
        //Given
        User user = new User(10L, "John", "Kovalsky", "johnkovalsky@mail.com");
        when(userRepository.save(user)).thenReturn(user);
        //When
        User resultUser = dbUser.saveUser(user);
        //Then
        assertEquals("Kovalsky",resultUser.getLastname());
    }
}
