package com.crud.cars.mapper;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getVin(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getYear(),
                carDto.getFuel(),
                carDto.getCylinderCapacity(),
                carDto.getColor());
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getVin(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getFuel(),
                car.getCylinderCapacity(),
                car.getColor());
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(c -> new CarDto(c.getId(), c.getVin(), c.getBrand(), c.getModel(),
                        c.getYear(), c.getFuel(), c.getCylinderCapacity(), c.getColor()))
                .collect(Collectors.toList());
    }
}
