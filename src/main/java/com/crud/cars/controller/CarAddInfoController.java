package com.crud.cars.controller;

import com.crud.cars.domain.CarAdditionalInfoDto;
import com.crud.cars.mapper.CarAdditionalInfoMapper;
import com.crud.cars.service.DbCarAdditionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costs/cars/")
@CrossOrigin(origins = "*")
public class CarAddInfoController {
    @Autowired
    private DbCarAdditionalInfo service;

    @Autowired
    private CarAdditionalInfoMapper mapper;

    @GetMapping(value = "/info")
    public List<CarAdditionalInfoDto> findAll() {
        return mapper.mapToCarAddInfoDtoList(service.findAllAddInfoList());
    }

    @GetMapping(value = "info/{id}")
    public CarAdditionalInfoDto findByCarAddInfo(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToCarAddInfoDto(service.findByCarAddId(id).orElseThrow(NotFoundException::new));
    }

    @PutMapping(value = "/info")
    public CarAdditionalInfoDto updateInfo(@RequestBody CarAdditionalInfoDto carAdditionalInfoDto) {
        return mapper.mapToCarAddInfoDto(service.save(mapper.mapToCarAddInfo(carAdditionalInfoDto)));
    }

    @DeleteMapping(value = "info/{id}")
    public void deleteCarAddInfo(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping(value = "/info")
    public void addNewCarAddInfo(@RequestBody CarAdditionalInfoDto carAdditionalInfoDto) {
        service.save(mapper.mapToCarAddInfo(carAdditionalInfoDto));
    }
}
