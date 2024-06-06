package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.repository.CarRepository;
import com.prodera.CarRegistry.service.CarService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;
    @Value("${car.default.make}")
    private String defaultCarMake;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void logDefaultCarMake() {
        logger.info(STR."Default Car Make: \{defaultCarMake}");
        logger.info(STR."Car from repository: \{carRepository.getCar()}");

    }
}
