package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCarData() {
        logger.info("Accessed the app.");
        carService.logDefaultCarMake();
        return "Check the logs for car data.";
    }
}
