package com.prodera.CarRegistry.repository;

import com.prodera.CarRegistry.repository.entity.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    CarEntity save(CarEntity carEntity);

    Optional<CarEntity> findById(Integer id);

    void updateCar(CarEntity carEntity);

    void deleteById(Integer id);

    List<CarEntity> findAll();
}
