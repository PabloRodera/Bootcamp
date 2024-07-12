package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.controller.mapper.CarMapper;
import com.prodera.CarRegistry.service.CarService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarMapper carMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public ResponseEntity<?> getCarById(@PathVariable Integer id) {
        log.info("Retrieving car info with id {}", id);
        CarDto carDto = carService.getCarById(id);
        if (carDto != null) {
            return new ResponseEntity<>(carDto, HttpStatus.OK);
        } else {
            log.info("No car info with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public CompletableFuture<List<CarDto>> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        log.info("Deleting car with id {}", STR."\{id}...");
        if (carService.getCar(id) != null) {
            carService.deleteCar(id);
            log.info("Car deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.info("Car could not be deleted correctly.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> addCar(@RequestBody CarDto carDto) {
        log.info("Saving car info...");
        try {
            CarDto savedCar = carService.addCar(carDto);
            log.info("Car added succesfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } catch (Exception e) {
            log.info("Car could not be added correctly.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody CarDto carDto) {
        log.info("Updating car info with id {}", STR."\{id}...");
        try {
            if (carService.getCar(id) != null) {
                carService.updateCar(id, carDto);
                log.info("Car info with id {}", STR."\{id} updated successfully!");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info("Car with id {}", STR."\{id} not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Internal server error.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/export")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public void exportCarsToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"cars.csv\"");
        carService.exportCarsToCsv(response.getWriter());
    }
    @PostMapping("/import")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<String> importCarsFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            carService.importCarsFromCsv(file);
            return ResponseEntity.ok("Cars imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing cars.");
        }
    }
}
