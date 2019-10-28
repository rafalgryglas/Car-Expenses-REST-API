package com.crud.cars.repository;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarAdditionalInfo;
import com.crud.cars.domain.FuelType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarAddInfoRepoTest {

    @Autowired
    private CarAdditionalInfoRepository repository;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void carAdditionalInfoRepoTest() {
        //Given
        Car car = new Car("YV1RS796123123123", "Volvo", "S60", 2006, FuelType.DIESEL, 2401, "silver");
        carRepository.save(car);
        CarAdditionalInfo carAdditionalInfo = new CarAdditionalInfo(car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        //When
        repository.save(carAdditionalInfo);
        Long id = carAdditionalInfo.getId();
        Optional<CarAdditionalInfo> getInfo = repository.findById(id);
        Long carId = car.getId();
        //Then
        Assert.assertEquals(id, getInfo.get().getId());
        //CleanUp
        repository.deleteById(id);
        carRepository.deleteById(carId);
    }
}
