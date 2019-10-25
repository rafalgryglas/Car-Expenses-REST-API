package com.crud.cars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Column(name = "VIN")
    @NotNull
    private String vin;

    @Column
    @NotNull
    private String brand;

    @Column
    @NotNull
    private String model;

    @Column(name = "YEAR_OF_MANUFACTURE")
    @NotNull
    private String year;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "Enum('PETROL','DIESEL','PETROL_LPG','PETROL_ELECTRICITY','HYBRID','ELECTRCITY')")
    @NotNull
    private FuelType fuel;

    @Column
    @NotNull
    private int cylinderCapacity;

    @Column
    private String color;
}
