package com.crud.cars.mapper;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarDto;
import com.crud.cars.domain.FuelType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarMapperTestSuite {

    @Autowired
    private CarMapper carMapper;

    @Test
    public void mapToCarTest() {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        //When
        Car car = carMapper.mapToCar(carDto);
        //Then
        assertEquals("S60", car.getModel());
    }

    @Test
    public void mapToCarDtoTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        //When
        CarDto carDto = carMapper.mapToCarDto(car);
        //Then
        assertEquals(2005, carDto.getYear());
    }

    @Test
    public void mapToCarDtoListTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        Car car2 = new Car(11L, "JM7GZF111111", "Mazda", "626V(GF)", 1997, FuelType.PETROL_LPG, 1800, "green");
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car2);
        //When
        List<CarDto> carDtoList = carMapper.mapToCarDtoList(carList);
        //Then
        assertEquals(2, carDtoList.size());
    }
}
