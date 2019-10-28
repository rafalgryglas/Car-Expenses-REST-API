package com.crud.cars.repository;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.FuelType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void carRepositoryTest() {
        //Given
        Car car = new Car("YV1RS796123123123", "Volvo", "S60", 2006, FuelType.DIESEL, 2401, "silver");
        //When
        carRepository.save(car);
        Long id = car.getId();
        Optional<Car> getCar = carRepository.findById(id);
        //Then
        Assert.assertEquals(id, getCar.get().getId());
        //CleanUp
        carRepository.deleteById(id);
    }
}
