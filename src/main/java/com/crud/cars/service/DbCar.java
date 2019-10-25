package com.crud.cars.service;

import com.crud.cars.domain.Car;
import com.crud.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbCar {

    @Autowired
    private CarRepository repository;

    public List<Car> findAllCars() {
        return repository.findAll();
    }

    public Optional<Car> findCar(Long id) {
        return repository.findById(id);
    }

    public Car saveCar(Car car) {
        return repository.save(car);
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
