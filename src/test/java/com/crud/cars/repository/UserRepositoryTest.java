package com.crud.cars.repository;

import com.crud.cars.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryTest() {
        //Given
        User user = new User("John", "Pawlak", "john.pawlak@johnpawlak.com");
        //When
        userRepository.save(user);
        Long id = user.getId();
        Optional<User> getUser = userRepository.findById(id);
        //Then
        Assert.assertEquals(id, getUser.get().getId());
        //CleanUp
        userRepository.deleteById(id);
    }
}
