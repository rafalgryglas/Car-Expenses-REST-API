package com.crud.cars.repository;

import com.crud.cars.domain.CarAdditionalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarAdditionalInfoRepository extends CrudRepository<CarAdditionalInfo, Long> {

    @Override
    List<CarAdditionalInfo> findAll();

    Optional<CarAdditionalInfo> findById(Long id);

    @Override
    CarAdditionalInfo save(CarAdditionalInfo carAdditionalInfo);

    @Override
    void deleteById(Long id);
}
