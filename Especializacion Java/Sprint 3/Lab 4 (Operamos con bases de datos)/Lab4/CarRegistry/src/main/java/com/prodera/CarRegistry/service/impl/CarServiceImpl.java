package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.controller.mapper.CarMapper;
import com.prodera.CarRegistry.repository.BrandRepository;
import com.prodera.CarRegistry.repository.CarRepository;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import com.prodera.CarRegistry.service.CarService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarMapper carMapper;

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
    @Transactional
    public CarDto addCar(CarDto carDto) {
        CarEntity carEntity = carMapper.toCarEntity(carDto);
        Optional<BrandEntity> brandEntityOptional = brandRepository.findById(carDto.getBrand_id());

        if (brandEntityOptional.isPresent()) {
           BrandEntity brandEntity = brandEntityOptional.get();
            carEntity.setBrand(brandEntity);

            CarEntity savedEntity = carRepository.save(carEntity);
            return carMapper.toCarDto(savedEntity);
        } else {
            log.error("Brand with ID {} not found", carDto.getBrand_id());
            throw new RuntimeException("Brand not found for ID: " + carDto.getBrand_id());
        }
    }

    @Override
    public CarDto getCarById(Integer id) {
        Optional<CarEntity> carEntity = carRepository.findById(id);
        return carEntity.map(carMapper::toCarDto).orElse(null);
    }

    @Override
    @Transactional
    public void updateCar(Integer id, CarDto carDto) {
        Optional<CarEntity> carEntityOptional = carRepository.findById(id);
        if (carEntityOptional.isPresent()) {
            CarEntity carEntity = carEntityOptional.get();
            carEntity.setModel(carDto.getModel());
            carEntity.setMilleage(carDto.getMilleage());
            carEntity.setPrice(carDto.getPrice());
            carEntity.setYear(carDto.getYear());
            carEntity.setDescription(carDto.getDescription());
            carEntity.setColour(carDto.getColour());
            carEntity.setFuelType(carDto.getFuelType());
            carEntity.setNumDoors(carDto.getNumDoors());

            Optional<BrandEntity> brandEntityOptional = brandRepository.findById(carDto.getBrand_id());
            if (brandEntityOptional.isPresent()) {
                carEntity.setBrand(brandEntityOptional.get());
                carRepository.save(carEntity);
            } else {
                log.error("Brand with ID {} not found", carDto.getBrand_id());
                throw new IllegalArgumentException("Brand with ID " + carDto.getBrand_id() + " not found");
            }
        } else {
            log.error("Car with ID {} not found", id);
            throw new IllegalArgumentException("Car with ID " + id + " not found");
        }
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
}
