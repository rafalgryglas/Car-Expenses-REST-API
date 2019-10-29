package com.crud.cars.controller;

import com.crud.cars.domain.CarDto;
import com.crud.cars.mapper.CarMapper;
import com.crud.cars.service.DbCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/costs")
public class CarController {
    @Autowired
    private DbCar service;

    @Autowired
    private CarMapper mapper;

    @GetMapping( value = "cars")
    public List<CarDto> getCars() {
        return mapper.mapToCarDtoList(service.findAllCars());
    }

    @GetMapping(value = "cars/{id}")
    public CarDto getCar(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToCarDto(service.findCar(id).orElseThrow(NotFoundException::new));
    }

    @PutMapping(value = "/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return mapper.mapToCarDto(service.saveCar(mapper.mapToCar(carDto)));
    }

    @DeleteMapping(value = "cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping(value = "cars")
    public void createCar(@RequestBody CarDto carDto) {
        service.saveCar(mapper.mapToCar(carDto));
    }
}
