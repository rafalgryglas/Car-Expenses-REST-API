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

    @RequestMapping(method = RequestMethod.GET, value = "getCars")
    public List<CarDto> getCars() {
        return mapper.mapToCarDtoList(service.findAllCars());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCar")
    public CarDto getCar(@RequestParam Long id) throws NotFoundException {
        return mapper.mapToCarDto(service.findCar(id).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCar")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return mapper.mapToCarDto(service.saveCar(mapper.mapToCar(carDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCar")
    public void deleteCar(@RequestParam Long id) {
        service.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCar")
    public void createCar(@RequestBody CarDto carDto) {
        service.saveCar(mapper.mapToCar(carDto));
    }
}
