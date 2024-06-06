package com.prodera.CarRegistry.repository.impl;

import com.prodera.CarRegistry.model.Car;
import com.prodera.CarRegistry.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private static final Logger logger = LoggerFactory.getLogger(CarRepositoryImpl.class);
    private Car car;

    @PostConstruct
    public void init() {
        car = new Car("Seat", "Ibiza", 2016);
        logger.info("Car created: " + car); //Aunque el log aqui no se muestre dado que no comunica con la capa de presentación, lo rastreo para posibles problemas.
    }

    @Override
    public Car getCar() {
        return car;
    }
}
