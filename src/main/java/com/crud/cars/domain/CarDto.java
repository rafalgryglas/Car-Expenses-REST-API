package com.crud.cars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private Long id;
    private String vin;
    private String brand;
    private String model;
    private String year;
    private FuelType fuel;
    private int cylinderCapacity;
    private String color;
}
