package com.crud.cars.controller;

import com.crud.cars.domain.Car;
import com.crud.cars.domain.CarDto;
import com.crud.cars.domain.FuelType;
import com.crud.cars.mapper.CarMapper;
import com.crud.cars.service.DbCar;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbCar dbCar;

    @MockBean
    private CarMapper carMapper;
    @Test
    public void shouldFetchGetCars() throws Exception {
        //Given
        CarDto car1 = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        List<CarDto> carListDto = new ArrayList<>();
        carListDto.add(car1);
        List<Car> carList = new ArrayList<>();
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carListDto);
        //When&Then
        mockMvc.perform(get("/costs/cars")
                .characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(10)))
                .andExpect(jsonPath("$[0].vin",is("YV1RS796111111")))
                .andExpect(jsonPath("$[0].brand",is("Volvo")))
                .andExpect(jsonPath("$[0].model",is("S60")))
                .andExpect(jsonPath("$[0].year",is(2005)))
                .andExpect(jsonPath("$[0].fuel",is("DIESEL")))
                .andExpect(jsonPath("$[0].cylinderCapacity",is(2401)))
                .andExpect(jsonPath("$[0].color",is("silver")));
    }
    @Test
    public void shouldFetchGetCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        when(dbCar.findCar(10L)).thenReturn(Optional.ofNullable(car));
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);
        //When&Then
        mockMvc.perform(get("/costs/cars/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(10)))
                .andExpect(jsonPath("$.vin",is("YV1RS796111111")))
                .andExpect(jsonPath("$.brand",is("Volvo")))
                .andExpect(jsonPath("$.model",is("S60")))
                .andExpect(jsonPath("$.year",is(2005)))
                .andExpect(jsonPath("$.fuel",is("DIESEL")))
                .andExpect(jsonPath("$.cylinderCapacity",is(2401)))
                .andExpect(jsonPath("$.color",is("silver")));
    }
    @Test
    public void shouldDeleteCar() throws Exception {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        dbCar.deleteById(car.getId());
        //When&Then
        mockMvc.perform(delete("/costs/cars/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        when(carMapper.mapToCarDto(dbCar.saveCar(carMapper.mapToCar(carDto)))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
                //When&Then
        mockMvc.perform(put("/costs/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(10)))
                .andExpect(jsonPath("$.vin",is("YV1RS796111111")))
                .andExpect(jsonPath("$.brand",is("Volvo")))
                .andExpect(jsonPath("$.model",is("S60")))
                .andExpect(jsonPath("$.year",is(2005)))
                .andExpect(jsonPath("$.fuel",is("DIESEL")))
                .andExpect(jsonPath("$.cylinderCapacity",is(2401)))
                .andExpect(jsonPath("$.color",is("silver")));
    }
}
