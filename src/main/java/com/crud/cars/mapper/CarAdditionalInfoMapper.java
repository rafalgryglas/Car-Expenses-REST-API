package com.crud.cars.mapper;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarAdditionalInfo;
import com.crud.cars.domain.CarAdditionalInfoDto;
import com.crud.cars.service.DbCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarAdditionalInfoMapper {

    @Autowired
    private DbCar service;

    public CarAdditionalInfo mapToCarAddInfo(final CarAdditionalInfoDto carAddInfoDto) {
        Car car = service.findCar(carAddInfoDto.getId()).orElse(null);
        return new CarAdditionalInfo(
                carAddInfoDto.getId(),
                car,
                carAddInfoDto.getRegistration(),
                carAddInfoDto.getInspectionDate(),
                carAddInfoDto.getInsuranceDate());
    }

    public CarAdditionalInfoDto mapToCarAddInfoDto(final CarAdditionalInfo carAddInfo) {
        return new CarAdditionalInfoDto(
                carAddInfo.getId(),
                carAddInfo.getCar().getId(),
                carAddInfo.getRegistration(),
                carAddInfo.getInspectionDate(),
                carAddInfo.getInsuranceDate());
    }

    public List<CarAdditionalInfoDto> mapToCarAddInfoDtoList(final List<CarAdditionalInfo> carAddInfoList) {
        return carAddInfoList.stream()
                .map(c -> new CarAdditionalInfoDto(c.getId(), c.getCar().getId(), c.getRegistration(),
                        c.getInspectionDate(), c.getInsuranceDate()))
                .collect(Collectors.toList());
    }
}
