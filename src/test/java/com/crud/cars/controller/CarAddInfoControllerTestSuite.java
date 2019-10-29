package com.crud.cars.controller;

import com.crud.cars.domain.*;
import com.crud.cars.mapper.CarAdditionalInfoMapper;
import com.crud.cars.service.DbCarAdditionalInfo;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarAddInfoController.class)
public class CarAddInfoControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbCarAdditionalInfo service;

    @MockBean
    private CarAdditionalInfoMapper mapper;

    @Test
    public void shouldFetchFindAll() throws Exception {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfoDto carAdditionalInfoDto = new CarAdditionalInfoDto(11L, carDto.getId(), "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        List<CarAdditionalInfoDto> listDto = new ArrayList<>();
        listDto.add(carAdditionalInfoDto);
        List<CarAdditionalInfo> list = new ArrayList<>();
        when(mapper.mapToCarAddInfoDtoList(list)).thenReturn(listDto);
        //When&Then
        mockMvc.perform(get("/costs/cars/info").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(11)))
                .andExpect(jsonPath("$[0].carId", is(10)))
                .andExpect(jsonPath("$[0].registration",is("WCI50000")))
                .andExpect(jsonPath("$[0].inspectionDate",is("2020-01-10")))
                .andExpect(jsonPath("$[0].insuranceDate",is("2019-12-12")));
    }
    @Test
    public void shouldFetchFindByCarAddInfo() throws Exception {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfoDto carAdditionalInfoDto = new CarAdditionalInfoDto(11L, carDto.getId(), "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        CarAdditionalInfo carAdditionalInfo = new CarAdditionalInfo(11L, car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        when(service.findByCarAddId(11L)).thenReturn(Optional.ofNullable(carAdditionalInfo));
        when(mapper.mapToCarAddInfoDto(carAdditionalInfo)).thenReturn(carAdditionalInfoDto);
        //When&Then
        mockMvc.perform(get("/costs/cars/info/11").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(11)))
                .andExpect(jsonPath("$.carId", is(10)))
                .andExpect(jsonPath("$.registration",is("WCI50000")))
                .andExpect(jsonPath("$.inspectionDate",is("2020-01-10")))
                .andExpect(jsonPath("$.insuranceDate",is("2019-12-12")));
    }
    @Test
    public void shouldUpdateInfo() throws Exception {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfoDto carAdditionalInfoDto = new CarAdditionalInfoDto(11L, carDto.getId(), "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        when(mapper.mapToCarAddInfoDto(service.save(mapper.mapToCarAddInfo(carAdditionalInfoDto)))).thenReturn(carAdditionalInfoDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carAdditionalInfoDto);
        //When&Then
        mockMvc.perform(put("costs/cars/info")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(11)))
                .andExpect(jsonPath("$.carId", is(10)))
                .andExpect(jsonPath("$.registration",is("WCI50000")))
                .andExpect(jsonPath("$.inspectionDate",is("2020-01-10")))
                .andExpect(jsonPath("$.insuranceDate",is("2019-12-12")));
    }
    @Test
    public void shouldDeleteCarAddInfo() throws Exception {
        //Given
        Car car = new Car(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfo carAddInfo = new CarAdditionalInfo(11L, car, "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        service.delete(carAddInfo.getId());
        //When&Then
        mockMvc.perform(delete("/costs/cars/info/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void shouldAddNewCarAddInfo() throws Exception {
        //Given
        CarDto carDto = new CarDto(10L, "YV1RS796111111", "Volvo", "S60", 2005, FuelType.DIESEL, 2401, "silver");
        CarAdditionalInfoDto carAdditionalInfoDto = new CarAdditionalInfoDto(11L, carDto.getId(), "WCI50000", LocalDate.of(2020, 1, 10), LocalDate.of(2019, 12, 12));
        CarAdditionalInfo carAdditionalInfo = new CarAdditionalInfo();
        when(service.save(mapper.mapToCarAddInfo(carAdditionalInfoDto))).thenReturn(carAdditionalInfo);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carAdditionalInfoDto);
        //When&Then
        mockMvc.perform(post("costs/cars/info")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
