package com.crud.cars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "CARS_ADDITIONAL_INFORMATION")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarAdditionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Car car;

    @Column
    private String registration;

    @Column
    private LocalDate inspectionDate;

    @Column
    private LocalDate insuranceDate;

    public CarAdditionalInfo(Car car, String registration, LocalDate inspectionDate, LocalDate insuranceDate) {
        this.car = car;
        this.registration = registration;
        this.inspectionDate = inspectionDate;
        this.insuranceDate = insuranceDate;
    }
}
