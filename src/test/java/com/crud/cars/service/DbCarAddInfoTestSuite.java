package com.crud.cars.service;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarAdditionalInfo;
import com.crud.cars.domain.FuelType;
import com.crud.cars.repository.CarAdditionalInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class DbCarAddInfoTestSuite {

    @Autowired
    private DbCarAdditionalInfo service;

    @MockBean
    private CarAdditionalInfoRepository repository;

    @Test
    public void findAllAddInfoListTest() {
        //Given
        Car car = new Car("YV1RS796123123123", "Volvo", "S60", 2006, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfo carAddInfo1 = new CarAdditionalInfo(car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        Car car2 = new Car(11L, "JM7GZF111111", "Mazda", "626V(GF)", 1997, FuelType.PETROL_LPG, 1800, "green");
        CarAdditionalInfo carAddInfo2 = new CarAdditionalInfo(12L, car2, "WCI16360", LocalDate.of(2020, 1, 26), LocalDate.of(2020, 7, 12));
        List<CarAdditionalInfo> carAdditionalInfoList = new ArrayList<>();
        carAdditionalInfoList.add(carAddInfo1);
        carAdditionalInfoList.add(carAddInfo2);
        when(repository.findAll()).thenReturn(carAdditionalInfoList);
        //When
        List<CarAdditionalInfo> resultList = service.findAllAddInfoList();
        //THen
        assertEquals(2, resultList.size());
    }

    @Test
    public void findByCarAddIdTest() {
        //Given
        Car car = new Car("YV1RS796123123123", "Volvo", "S60", 2006, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfo carAddInfo = new CarAdditionalInfo(15L, car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        when(repository.findById(15L)).thenReturn(Optional.ofNullable(carAddInfo));
        //When
        Optional<CarAdditionalInfo> result = service.findByCarAddId(15L);
        //THen
        assertEquals("WCI50000", result.get().getRegistration());
    }

    @Test
    public void saveCarInfoTest() {
        //Given
        Car car = new Car("YV1RS796123123123", "Volvo", "S60", 2006, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfo carAddInfo = new CarAdditionalInfo(15L, car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        when(repository.save(carAddInfo)).thenReturn(carAddInfo);
        //When
        CarAdditionalInfo result = service.save(carAddInfo);
        //THen
        assertEquals("WCI50000", result.getRegistration());
    }

}
