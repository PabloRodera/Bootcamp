package com.prodera.CarRegistry.controller.mapper;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.repository.BrandRepository;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    @Autowired
    private BrandRepository brandRepository;

    public CarEntity toCarEntity(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDto.getId());

        BrandEntity brandEntity = brandRepository.findById(carDto.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("BrandEntity with id " + carDto.getBrandId() + " not found"));
        carEntity.setBrand(brandEntity);

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

    public CarDto toCarDto(CarEntity carEntity) {
        CarDto carDto = new CarDto();
        carDto.setId(carEntity.getId());
        carDto.setBrandId(carEntity.getBrand().getId());
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
}
