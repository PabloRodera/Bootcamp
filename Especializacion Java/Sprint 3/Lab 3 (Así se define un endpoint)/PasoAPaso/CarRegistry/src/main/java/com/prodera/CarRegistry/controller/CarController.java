package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dtos.GreetingResponse;
import com.prodera.CarRegistry.controller.dtos.GreetingsRequest;
import com.prodera.CarRegistry.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    List<GreetingResponse> greetingList = new ArrayList<>();
    @GetMapping("/cars")
    public String getCarData() {
        logger.info("Accessed the app.");
        carService.logDefaultCarMake();
        return "Check the logs for car data.";
    }

    @PostMapping("/hello")
    public ResponseEntity<?> addGreeting(@RequestBody GreetingsRequest greetingsRequest) {
        System.out.println("Greetings from: " + greetingsRequest.getPersonName() + " with text: " + greetingsRequest.getGreeting());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/hello/{id}")
    public ResponseEntity<?> deleteGreeting(@PathVariable Integer id) {
        System.out.println("The id to delete is: " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hello/{id}")
    public ResponseEntity<?> alterGreeting(@PathVariable Integer id, @RequestBody GreetingsRequest greetingsRequest) {
        System.out.println("Greetings from: " + greetingsRequest.getPersonName() + " with text: " + greetingsRequest.getGreeting());
        System.out.println("The id alter is: " + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("hello/{id}")
    public ResponseEntity<?> getGreeting(@PathVariable Integer id) {
        GreetingResponse greeting = new GreetingResponse();
        greeting.setId(1);
        greeting.setGreeting("Hello");
        greeting.setPersonName("Pablo");
        greetingList.add(greeting);

        try {
            GreetingResponse response = greetingList.stream().filter(g -> g.getId() == id).findFirst().get();
            return ResponseEntity.ok(response);
        }
        catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            log.error("Fallo fatal");
            return ResponseEntity.internalServerError().build();
        }
    }
}
