package com.crud.cars.mapper;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarAdditionalInfo;
import com.crud.cars.domain.CarAdditionalInfoDto;
import com.crud.cars.domain.FuelType;
import com.crud.cars.service.DbCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarAddInfoMapperTestSuite {

    @Autowired
    private CarAdditionalInfoMapper mapper;

    @MockBean
    private DbCar dbCar;

    @Test
    public void mapToCarAddInfoTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfoDto carAddInfoDto = new CarAdditionalInfoDto(11L, car.getId(), "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        when(dbCar.findCar(carAddInfoDto.getCarId())).thenReturn(Optional.ofNullable(car));
        //When
        CarAdditionalInfo carInfo = mapper.mapToCarAddInfo(carAddInfoDto);
        //Then
        assertEquals("WCI50000", carInfo.getRegistration());
    }

    @Test
    public void mapToCarAddInfoDtoTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfo carAddInfo = new CarAdditionalInfo(11L, car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        //When
        CarAdditionalInfoDto carAdditionalInfoDto = mapper.mapToCarAddInfoDto(carAddInfo);
        //Then
        assertEquals("WCI50000", carAdditionalInfoDto.getRegistration());
    }

    @Test
    public void mapToCarAddInfoDtoListTest() {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        Car car2 = new Car(11L, "JM7GZF111111", "Mazda", "626V(GF)", 1997, FuelType.PETROL_LPG, 1800, "green");
        CarAdditionalInfo carAddInfo1 = new CarAdditionalInfo(11L, car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        CarAdditionalInfo carAddInfo2 = new CarAdditionalInfo(12L, car2, "WCI16360", LocalDate.of(2020, 1, 26), LocalDate.of(2020, 7, 12));
        List<CarAdditionalInfo> carAdditionalInfoList = new ArrayList<>();
        carAdditionalInfoList.add(carAddInfo1);
        carAdditionalInfoList.add(carAddInfo2);
        //When
        List<CarAdditionalInfoDto> carAdditionalInfoDtoList = mapper.mapToCarAddInfoDtoList(carAdditionalInfoList);
        //Then
        assertEquals(2, carAdditionalInfoDtoList.size());
        assertEquals("WCI50000", carAdditionalInfoDtoList.get(0).getRegistration());
        assertEquals(car2.getId(), carAdditionalInfoDtoList.get(1).getCarId());
    }
}
