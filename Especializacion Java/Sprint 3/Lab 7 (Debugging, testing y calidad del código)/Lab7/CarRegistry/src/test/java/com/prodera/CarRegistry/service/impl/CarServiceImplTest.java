package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.controller.mapper.CarMapper;
import com.prodera.CarRegistry.repository.BrandRepository;
import com.prodera.CarRegistry.repository.CarRepository;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private CarDto carDto1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        CarEntity carEntity1 = new CarEntity();
        carEntity1.setId(1);
        carEntity1.setModel("Modelo1");
        carEntity1.setMilleage(10000);
        carEntity1.setPrice(25000.0);
        carEntity1.setYear(2020);
        carEntity1.setDescription("Descripcion1");
        carEntity1.setColour("Rojo");
        carEntity1.setFuelType("Gasolina");
        carEntity1.setNumDoors(4);

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);
        brandEntity.setName("Marca1");
        brandEntity.setWarranty(3);
        brandEntity.setCountry("Pais1");

        carEntity1.setBrand(brandEntity);

        carDto1 = new CarDto();
        carDto1.setId(1);
        carDto1.setModel("Modelo1");
        carDto1.setMilleage(10000);
        carDto1.setPrice(25000.0);
        carDto1.setYear(2020);
        carDto1.setDescription("Descripcion1");
        carDto1.setColour("Rojo");
        carDto1.setFuelType("Gasolina");
        carDto1.setNumDoors(4);
        carDto1.setBrand_id(1);

        when(carMapper.toCarEntity(any(CarDto.class))).thenReturn(carEntity1);
        when(carMapper.toCarDto(any(CarEntity.class))).thenReturn(carDto1);
        when(brandRepository.findById(1)).thenReturn(Optional.of(brandEntity));
        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity1);
        when(carRepository.findById(1)).thenReturn(Optional.of(carEntity1));
    }

    @Test
    void addCar() {
        CarDto savedCar = carService.addCar(carDto1);

        assertNotNull(savedCar);
        assertEquals("Modelo1", savedCar.getModel());
        assertEquals(10000, savedCar.getMilleage());
        assertEquals(25000.0, savedCar.getPrice());
        assertEquals(2020, savedCar.getYear());
        assertEquals("Descripcion1", savedCar.getDescription());
        assertEquals("Rojo", savedCar.getColour());
        assertEquals("Gasolina", savedCar.getFuelType());
        assertEquals(4, savedCar.getNumDoors());
        assertEquals(1, savedCar.getBrand_id());

        verify(carRepository, times(1)).save(any(CarEntity.class));
    }

    @Test
    void getCar() {
        CarDto car = carService.getCar(1);

        assertNotNull(car);
        assertEquals("Modelo1", car.getModel());
        assertEquals(10000, car.getMilleage());
        assertEquals(25000.0, car.getPrice());
        assertEquals(2020, car.getYear());
        assertEquals("Descripcion1", car.getDescription());
        assertEquals("Rojo", car.getColour());
        assertEquals("Gasolina", car.getFuelType());
        assertEquals(4, car.getNumDoors());
        assertEquals(1, car.getBrand_id());

        verify(carRepository, times(1)).findById(1);
    }

    @Test
    void updateCar() {
        CarDto updatedCarDto = new CarDto();
        updatedCarDto.setModel("Modelo Actualizado");
        updatedCarDto.setMilleage(20000);
        updatedCarDto.setPrice(30000.0);
        updatedCarDto.setYear(2022);
        updatedCarDto.setDescription("Descripcion Actualizada");
        updatedCarDto.setColour("Azul");
        updatedCarDto.setFuelType("Diesel");
        updatedCarDto.setNumDoors(2);
        updatedCarDto.setBrand_id(1);

        carService.updateCar(1, updatedCarDto);

        verify(carRepository, times(1)).findById(1);
        verify(carRepository, times(1)).save(any(CarEntity.class));
    }

    @Test
    void deleteCar() {
        carService.deleteCar(1);

        verify(carRepository, times(1)).deleteById(1);
    }

    @Test
    void getAllCars() {
        carService.getAllCars();

        verify(carRepository, times(1)).findAll();
    }
}
