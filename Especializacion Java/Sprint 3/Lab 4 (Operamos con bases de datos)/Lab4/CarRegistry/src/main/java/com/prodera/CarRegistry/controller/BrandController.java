package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.BrandDto;
import com.prodera.CarRegistry.controller.dto.CarDto;
import com.prodera.CarRegistry.controller.mapper.BrandMapper;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import com.prodera.CarRegistry.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandMapper brandMapper;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        List<BrandDto> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        log.info("Retrieving brand info with id {}", id);
        BrandDto brandDto = brandService.getBrandById(id);
        if (brandDto != null) {
            return new ResponseEntity<>(brandDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addBrand")
    public ResponseEntity<?> addBrand(@RequestBody BrandDto brandDto) {
        log.info("Saving brand info...");
        try {
            BrandDto savedBrand = brandService.addBrand(brandDto);
            log.info("brand added succesfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
        } catch (Exception e) {
            log.info("Brand could not be added correctly.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateBrand/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer id, @RequestBody BrandDto brandDto) {
        log.info("Updating brand info with id {}", id);
        try {
            if (brandService.getBrandById(id) != null) {
                brandService.updateBrand(id, brandDto);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteBrand/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        log.info("Deleting brand with id {}", id);
        if (brandService.getBrandById(id) != null) {
            brandService.deleteBrand(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
