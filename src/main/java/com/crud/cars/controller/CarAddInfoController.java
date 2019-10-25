package com.crud.cars.controller;

import com.crud.cars.domain.CarAdditionalInfoDto;
import com.crud.cars.mapper.CarAdditionalInfoMapper;
import com.crud.cars.service.DbCarAdditionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costs/info")
@CrossOrigin(origins = "*")
public class CarAddInfoController {
    @Autowired
    private DbCarAdditionalInfo service;

    @Autowired
    private CarAdditionalInfoMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllCarsInfo")
    public List<CarAdditionalInfoDto> findAll() {
        return mapper.mapToCarAddInfoDtoList(service.carAdditionalInfoList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getInfo")
    public CarAdditionalInfoDto findByCarAddInfo(@RequestParam Long id) throws NotFoundException {
        return mapper.mapToCarAddInfoDto(service.findByCarAddId(id).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateInfo")
    public CarAdditionalInfoDto updateInfo(@RequestBody CarAdditionalInfoDto carAdditionalInfoDto) {
        return mapper.mapToCarAddInfoDto(service.save(mapper.mapToCarAddInfo(carAdditionalInfoDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteInfo")
    public void deleteCarAddInfo(@RequestParam Long id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addInfo")
    public void addNewCarAddInfo(@RequestBody CarAdditionalInfoDto carAdditionalInfoDto) {
        service.save(mapper.mapToCarAddInfo(carAdditionalInfoDto));
    }
}
