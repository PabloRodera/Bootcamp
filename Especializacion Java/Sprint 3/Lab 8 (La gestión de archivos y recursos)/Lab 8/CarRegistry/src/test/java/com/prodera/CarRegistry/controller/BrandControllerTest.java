package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.BrandDto;
import com.prodera.CarRegistry.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandControllerTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBrands_ReturnsListOfBrands() {
        BrandDto brand1 = new BrandDto(1, "Marca1", 2, "España");
        BrandDto brand2 = new BrandDto(2, "Marca2", 5 , "Italia");
        List<BrandDto> mockBrands = Arrays.asList(brand1, brand2);

        when(brandService.getAllBrands()).thenReturn(CompletableFuture.completedFuture(mockBrands));

        CompletableFuture<List<BrandDto>> result = brandController.getAllBrands();

        assertEquals(mockBrands, result.join());
    }

    @Test
    void getBrandById_ExistingBrand_ReturnsBrandDto() {
        int brandId = 1;
        BrandDto mockBrand = new BrandDto(brandId, "Marca1", 2, "España");

        when(brandService.getBrandById(brandId)).thenReturn(mockBrand);

        ResponseEntity<?> responseEntity = brandController.getBrandById(brandId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBrand, responseEntity.getBody());
    }

    @Test
    void getBrandById_NonExistingBrand_ReturnsNotFound() {
        int brandId = 1;

        when(brandService.getBrandById(brandId)).thenReturn(null);

        ResponseEntity<?> responseEntity = brandController.getBrandById(brandId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addBrand_ValidBrandDto_ReturnsCreated() {
        BrandDto brandDto = new BrandDto(1, "Marca1", 2, "España");

        when(brandService.addBrand(any(BrandDto.class))).thenReturn(brandDto);

        ResponseEntity<?> responseEntity = brandController.addBrand(brandDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(brandDto, responseEntity.getBody());
    }

    @Test
    void addBrand_InvalidBrandDto_ReturnsInternalServerError() {
        BrandDto invalidBrandDto = new BrandDto();

        ResponseEntity<?> responseEntity = brandController.addBrand(invalidBrandDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void updateBrand_ExistingBrand_ReturnsOk() {
        int brandId = 1;
        BrandDto brandDto = new BrandDto(brandId, "MarcaActualizada", 10, "Portugal");

        when(brandService.getBrandById(brandId)).thenReturn(new BrandDto(brandId, "Marca1", 2, "España"));


        ResponseEntity<?> responseEntity = brandController.updateBrand(brandId, brandDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(brandService, times(1)).updateBrand(eq(brandId), any(BrandDto.class));
    }

    @Test
    void updateBrand_NonExistingBrand_ReturnsNotFound() {
        int brandId = 1;
        BrandDto brandDto = new BrandDto(brandId, "MarcaActualizada", 10, "Portugal");

        when(brandService.getBrandById(brandId)).thenReturn(null);

        ResponseEntity<?> responseEntity = brandController.updateBrand(brandId, brandDto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(brandService, never()).updateBrand(anyInt(), any(BrandDto.class));
    }

    @Test
    void deleteBrand_ExistingBrand_ReturnsOk() {
        int brandId = 1;

        when(brandService.getBrandById(brandId)).thenReturn(new BrandDto(brandId, "Marca1", 2, "España"));

        ResponseEntity<?> responseEntity = brandController.deleteBrand(brandId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


        verify(brandService, times(1)).deleteBrand(brandId);
    }

    @Test
    void deleteBrand_NonExistingBrand_ReturnsNotFound() {
        int brandId = 1;

        when(brandService.getBrandById(brandId)).thenReturn(null);

        ResponseEntity<?> responseEntity = brandController.deleteBrand(brandId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(brandService, never()).deleteBrand(anyInt());
    }
}
