package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCars_ReturnsListOfCars() {
        CarDto car1 = new CarDto(1, 1, "Toyota", 10000, 20000.0, 2023, "Descripcion1", "Rojo", "Gasolina", 3);
        CarDto car2 = new CarDto(2, 2, "Honda", 8000, 15000.0, 2022, "Descripcion2", "Azul", "Diesel", 5);
        List<CarDto> mockCars = Arrays.asList(car1, car2);

        when(carService.getAllCars()).thenReturn(CompletableFuture.completedFuture(mockCars));

        CompletableFuture<List<CarDto>> result = carController.getAllCars();

        assertEquals(mockCars, result.join());
    }

    @Test
    void getCarById_ExistingCar_ReturnsCarDto() {
        int carId = 1;
        CarDto mockCar = new CarDto(carId, 1, "Toyota", 10000, 20000.0, 2023, "Descripcion", "Rojo", "Gasolina", 3);

        when(carService.getCarById(carId)).thenReturn(mockCar);

        ResponseEntity<?> responseEntity = carController.getCarById(carId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCar, responseEntity.getBody());
    }

    @Test
    void getCarById_NonExistingCar_ReturnsNotFound() {
        int carId = 1;

        when(carService.getCarById(carId)).thenReturn(null);

        ResponseEntity<?> responseEntity = carController.getCarById(carId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addCar_ValidCarDto_ReturnsCreated() {
        CarDto carDto = new CarDto(1, 1, "Toyota", 10000, 20000.0, 2023, "Descripcion", "Rojo", "Gasolina", 3);

        when(carService.addCar(any(CarDto.class))).thenReturn(carDto);

        ResponseEntity<?> responseEntity = carController.addCar(carDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(carDto, responseEntity.getBody());
    }

    @Test
    void addCar_InvalidCarDto_ReturnsInternalServerError() {
        CarDto invalidCarDto = new CarDto();

        ResponseEntity<?> responseEntity = carController.addCar(invalidCarDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void updateCar_ExistingCar_ReturnsOk() {
        int carId = 1;
        CarDto carDto = new CarDto(carId, 1, "ModeloActualzado", 8000, 18000.0, 2022, "DescripcionActualizada", "Verde", "Diesel", 5);

        when(carService.getCar(carId)).thenReturn(new CarDto(carId, 1, "Toyota", 10000, 20000.0, 2023, "Descripcion", "Rojo", "Gasolina", 3));

        ResponseEntity<?> responseEntity = carController.updateCar(carId, carDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(carService, times(1)).updateCar(eq(carId), any(CarDto.class));
    }

    @Test
    void updateCar_NonExistingCar_ReturnsNotFound() {
        int carId = 1;
        CarDto carDto = new CarDto(carId, 1, "ModeloActualizado", 8000, 18000.0, 2022, "DescripcionActualizada", "Verde", "Diesel", 5);
        when(carService.getCar(carId)).thenReturn(null);

        ResponseEntity<?> responseEntity = carController.updateCar(carId, carDto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(carService, never()).updateCar(anyInt(), any(CarDto.class));
    }

    @Test
    void deleteCar_ExistingCar_ReturnsOk() {
        int carId = 1;

        when(carService.getCar(carId)).thenReturn(new CarDto(carId, 1, "Toyota",  10000, 20000.0, 2023, "Descripcion", "Rojo", "Gasolina", 3));

        ResponseEntity<?> responseEntity = carController.deleteCar(carId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(carService, times(1)).deleteCar(carId);
    }

    @Test
    void deleteCar_NonExistingCar_ReturnsNotFound() {
        int carId = 1;

        when(carService.getCar(carId)).thenReturn(null);

        ResponseEntity<?> responseEntity = carController.deleteCar(carId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(carService, never()).deleteCar(anyInt());
    }
}