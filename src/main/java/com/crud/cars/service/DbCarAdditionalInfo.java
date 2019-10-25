package com.crud.cars.service;

import com.crud.cars.domain.CarAdditionalInfo;
import com.crud.cars.repository.CarAdditionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbCarAdditionalInfo {

    @Autowired
    private CarAdditionalInfoRepository repository;

    public List<CarAdditionalInfo> carAdditionalInfoList() {
        return repository.findAll();
    }

    public Optional<CarAdditionalInfo> findByCarAddId(Long id) {
        return repository.findById(id);
    }

    public CarAdditionalInfo save(CarAdditionalInfo carAdditionalInfo) {
        return repository.save(carAdditionalInfo);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
