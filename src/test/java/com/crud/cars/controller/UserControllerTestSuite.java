package com.crud.cars.controller;

import com.crud.cars.domain.User;
import com.crud.cars.domain.UserDto;
import com.crud.cars.mapper.UserMapper;
import com.crud.cars.service.DbUser;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbUser dbUser;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldFetchGetUsers() throws Exception {
        //Given
        UserDto userDto = new UserDto(10L, "John", "Smith", "john.smith@john.com");
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        List<User> userList = new ArrayList<>();
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);
        //When&Then
        mockMvc.perform(get("/costs/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].firstname", is("John")))
                .andExpect(jsonPath("$[0].lastname", is("Smith")))
                .andExpect(jsonPath("$[0].email", is("john.smith@john.com")));
    }

    @Test
    public void shouldFetchGetUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(10L, "John", "Smith", "john.smith@john.com");
        User user = new User(10L, "John", "Smith", "john.smith@john.com");
        when(dbUser.getUser(10L)).thenReturn(Optional.ofNullable(user));
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        //When&Then
        mockMvc.perform(get("/costs/users/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Smith")))
                .andExpect(jsonPath("$.email", is("john.smith@john.com")));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        User user = new User(10L, "John", "Smith", "john.smith@john.com");
        dbUser.deleteByUserId(user.getId());
        //When&Then
        mockMvc.perform(delete("/costs/users/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(10L, "John", "Smith", "john.smith@john.com");
        when(userMapper.mapToUserDto(dbUser.saveUser(userMapper.mapToUser(userDto)))).thenReturn(userDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When&Then
        mockMvc.perform(put("/costs/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Smith")))
                .andExpect(jsonPath("$.email", is("john.smith@john.com")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        User user = new User();
        UserDto userDto = new UserDto(10L, "John", "Smith", "john.smith@john.com");
        when(dbUser.saveUser(userMapper.mapToUser(userDto))).thenReturn(user);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When&Then
        mockMvc.perform(post("/costs/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
