package com.prodera.CarRegistry.repository.impl;

import com.prodera.CarRegistry.repository.CarRepository;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CarRepositoryImpl implements CarRepository {
    private final List<CarEntity> carEntities = new ArrayList<>();
    @Override
    public CarEntity save(CarEntity carEntity) {
        carEntities.add(carEntity);
        return carEntity;
    }

    @Override
    public Optional<CarEntity> findById(Integer id) {
        return carEntities.stream().filter(car -> car.getId().equals(id)).findFirst();
    }
    @Override
    public void updateCar(CarEntity updatedCar) {
        findById(updatedCar.getId()).ifPresent(car -> {
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setMilleage(updatedCar.getMilleage());
            car.setPrice(updatedCar.getPrice());
            car.setYear(updatedCar.getYear());
            car.setDescription(updatedCar.getDescription());
            car.setColour(updatedCar.getColour());
            car.setFuelType(updatedCar.getFuelType());
            car.setNumDoors(updatedCar.getNumDoors());
        });
    }

    @Override
    public void deleteById(Integer id) {
        carEntities.removeIf(car -> car.getId().equals(id));
    }
    @Override
    public List<CarEntity> findAll() {
        return carEntities;
    }
}
