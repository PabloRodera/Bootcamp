package com.prodera.CarRegistry.controller.mapper;

import org.springframework.stereotype.Component;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import com.prodera.CarRegistry.controller.dto.CarDto;
@Component
public class CarMapper {
    public CarDto toCarDto(CarEntity carEntity) {
        CarDto carDto = new CarDto();
        carDto.setId(carEntity.getId());
        carDto.setBrand(carEntity.getBrand());
        carDto.setModel(carEntity.getModel());
        carDto.setMilleage(carEntity.getMilleage());
        carDto.setPrice(carEntity.getPrice());
        carDto.setYear(carEntity.getYear());
        carDto.setDescription(carEntity.getDescription());
        carDto.setColour(carEntity.getColour());
        carDto.setFuelType(carEntity.getFuelType());
        carDto.setNumDoors(carEntity.getNumDoors());
        return carDto;
    }

    public CarEntity toCarEntity(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDto.getId());
        carEntity.setBrand(carDto.getBrand());
        carEntity.setModel(carDto.getModel());
        carEntity.setMilleage(carDto.getMilleage());
        carEntity.setPrice(carDto.getPrice());
        carEntity.setYear(carDto.getYear());
        carEntity.setDescription(carDto.getDescription());
        carEntity.setColour(carDto.getColour());
        carEntity.setFuelType(carDto.getFuelType());
        carEntity.setNumDoors(carDto.getNumDoors());
        return carEntity;
    }
}
