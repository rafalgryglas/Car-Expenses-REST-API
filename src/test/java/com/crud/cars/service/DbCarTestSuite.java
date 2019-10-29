package com.crud.cars.service;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.FuelType;
import com.crud.cars.repository.CarRepository;
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
@SpringBootTest
@RunWith(SpringRunner.class)
public class DbCarTestSuite {

    @Autowired
    private DbCar dbCar;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void findAllCarsTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        Car car2 = new Car(11L, "JM7GZF111111", "Mazda", "626V(GF)", 1997, FuelType.PETROL_LPG, 1800, "green");
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car2);
        when(carRepository.findAll()).thenReturn(carList);
        //When
        List<Car> expectedList = dbCar.findAllCars();
        //Then
        assertEquals(2, expectedList.size());
    }

    @Test
    public void findCarTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        when(carRepository.findById(10L)).thenReturn(Optional.ofNullable(car));
        //When
        Optional<Car> car1 = dbCar.findCar(10L);
        //Then
        assertEquals("Volvo", car1.get().getBrand());
    }

    @Test
    public void saveCarTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        when(carRepository.save(car)).thenReturn(car);
        //When
        Car car1 = dbCar.saveCar(car);
        //Then
        assertEquals("S60", car1.getModel());
    }
}
