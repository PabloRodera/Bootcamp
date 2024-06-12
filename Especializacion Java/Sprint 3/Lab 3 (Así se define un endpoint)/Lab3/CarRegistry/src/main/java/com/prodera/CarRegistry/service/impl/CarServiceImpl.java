package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.controller.mapper.CarMapper;
import com.prodera.CarRegistry.repository.CarRepository;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import com.prodera.CarRegistry.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarMapper carMapper;
    @Value("${property.name}")
    private String name;

    @Override
    public CarDto getCar(Integer id) {
        Optional<CarEntity> carEntityOptional = carRepository.findById(id);
        if (carEntityOptional.isPresent()) {
            CarEntity carEntity = carEntityOptional.get();
            return carMapper.toCarDto(carEntity);
        } else {
            log.error("Car with ID {} not found", id);
            return null;
        }
    }

    @Override
    public CarDto addCar(CarDto carDto) {
        CarEntity carEntity = carMapper.toCarEntity(carDto);
        CarEntity savedEntity = carRepository.save(carEntity);
        return carMapper.toCarDto(savedEntity);
    }

    @Override
    public CarDto getCarById(Integer id) {
        Optional<CarEntity> carEntity = carRepository.findById(id);
        return carEntity.map(carMapper::toCarDto).orElse(null);
    }

    @Override
    public void updateCar(Integer id, CarDto carDto) {
        CarEntity carEntity = carMapper.toCarEntity(carDto);
        carEntity.setId(id);
        carRepository.updateCar(carEntity);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDto> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll();
        return carEntities.stream().map(carMapper::toCarDto).collect(Collectors.toList());
    }

    @Override
    public void saveCar(CarDto carDto) {
        CarEntity carEntity = carMapper.toCarEntity(carDto);
        carRepository.save(carEntity);
        log.info("Car saved successfully: {}", carDto);
    }
}
