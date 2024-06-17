package com.prodera.CarRegistry.service;

import com.prodera.CarRegistry.controller.dto.CarDto;
import java.util.List;

public interface CarService {

    CarDto getCar(Integer id);

    CarDto addCar(CarDto carDto);

    CarDto getCarById(Integer id);

    void updateCar(Integer id, CarDto carDto);

    void deleteCar(Integer id);

    List<CarDto> getAllCars();
}
