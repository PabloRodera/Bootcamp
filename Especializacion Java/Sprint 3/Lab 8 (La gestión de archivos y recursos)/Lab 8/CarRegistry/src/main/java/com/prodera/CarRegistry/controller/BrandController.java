package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.BrandDto;
import com.prodera.CarRegistry.controller.mapper.BrandMapper;
import com.prodera.CarRegistry.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandMapper brandMapper;

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public CompletableFuture<List<BrandDto>> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        log.info("Retrieving brand info with id {}", id);
        BrandDto brandDto = brandService.getBrandById(id);
        if (brandDto != null) {
            return new ResponseEntity<>(brandDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> addBrand(@RequestBody BrandDto brandDto) {
        log.info("Saving brand info...");
        try {
            BrandDto savedBrand = brandService.addBrand(brandDto);
            log.info("brand added successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
        } catch (Exception e) {
            log.info("Brand could not be added correctly.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        log.info("Deleting brand with id {}", id);
        if (brandService.getBrandById(id) != null) {
            brandService.deleteBrand(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
