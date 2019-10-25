package com.crud.cars.repository;

import com.crud.cars.domain.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    Optional<Car> findById(Long id);

    @Override
    Car save(Car car);

    @Override
    void deleteById(Long id);

}
