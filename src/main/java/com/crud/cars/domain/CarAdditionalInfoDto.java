package com.crud.cars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarAdditionalInfoDto {

    private Long id;
    private Long carId;
    private String registration;
    private LocalDate inspectionDate;
    private LocalDate insuranceDate;
}
