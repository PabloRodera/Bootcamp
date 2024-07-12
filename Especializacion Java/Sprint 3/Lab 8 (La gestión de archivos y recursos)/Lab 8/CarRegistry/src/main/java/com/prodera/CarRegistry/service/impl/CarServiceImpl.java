package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.controller.mapper.CarMapper;
import com.prodera.CarRegistry.repository.BrandRepository;
import com.prodera.CarRegistry.repository.CarRepository;
import com.prodera.CarRegistry.repository.entity.CarEntity;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import com.prodera.CarRegistry.service.CarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
        Optional<BrandEntity> brandEntityOptional = brandRepository.findById(carDto.getBrandId());

        if (brandEntityOptional.isPresent()) {
           BrandEntity brandEntity = brandEntityOptional.get();
            carEntity.setBrand(brandEntity);

            CarEntity savedEntity = carRepository.save(carEntity);
            return carMapper.toCarDto(savedEntity);
        } else {
            log.error("Brand with ID {} not found", carDto.getBrandId());
            throw new RuntimeException("Brand not found for ID: " + carDto.getBrandId());
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

            Optional<BrandEntity> brandEntityOptional = brandRepository.findById(carDto.getBrandId());
            if (brandEntityOptional.isPresent()) {
                carEntity.setBrand(brandEntityOptional.get());
                carRepository.save(carEntity);
            } else {
                log.error("Brand with ID {} not found", carDto.getBrandId());
                throw new IllegalArgumentException("Brand with ID " + carDto.getBrandId() + " not found");
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
    @Async("taskExecutor")
    public CompletableFuture<List<CarDto>> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll();
        return CompletableFuture.completedFuture(carEntities.stream().map(carMapper::toCarDto).collect(Collectors.toList()));
    }

    @Override
    public void exportCarsToCsv(Writer writer) throws IOException {
        List<CarEntity> cars = carRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("ID", "Model", "Milleage", "Price", "Year", "Description", "Colour", "Fuel Type", "Num Doors", "Brand"))) {

            for (CarEntity car : cars) {
                csvPrinter.printRecord(
                        car.getId(),
                        car.getModel(),
                        car.getMilleage(),
                        car.getPrice(),
                        car.getYear(),
                        car.getDescription(),
                        car.getColour(),
                        car.getFuelType(),
                        car.getNumDoors(),
                        car.getBrand().getName()
                );
            }
        }
    }

    @Override
    public void importCarsFromCsv(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                CarEntity car = new CarEntity();
                car.setModel(record.get("Model"));
                car.setMilleage(Integer.parseInt(record.get("Milleage")));
                car.setPrice(Double.parseDouble(record.get("Price")));
                car.setYear(Integer.parseInt(record.get("Year")));
                car.setDescription(record.get("Description"));
                car.setColour(record.get("Colour"));
                car.setFuelType(record.get("Fuel Type"));
                car.setNumDoors(Integer.parseInt(record.get("Num Doors")));

                BrandEntity brand = brandRepository.findByName(record.get("Brand")).orElseThrow(() -> new NoSuchElementException("Brand not found"));
                car.setBrand(brand);

                carRepository.save(car);
            }
        }
    }
}
