package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.BrandDto;
import com.prodera.CarRegistry.controller.mapper.BrandMapper;
import com.prodera.CarRegistry.repository.BrandRepository;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    private BrandEntity brandEntity1;
    private BrandEntity brandEntity2;
    private BrandDto brandDto1;
    private BrandDto brandDto2;

    @BeforeEach
    void setUp() {
        brandEntity1 = new BrandEntity(1, "Marca1", 5, "España", null);
        brandEntity2 = new BrandEntity(2, "Marca2", 3, "Japón", null);

        brandDto1 = new BrandDto(1, "Marca1", 5, "España");
        brandDto2 = new BrandDto(2, "Marca2", 3, "Japón");
    }

    @Test
    void addBrand() {
        when(brandMapper.toBrandEntity(any(BrandDto.class))).thenReturn(brandEntity1);
        when(brandRepository.save(any(BrandEntity.class))).thenReturn(brandEntity1);
        when(brandMapper.toBrandDto(any(BrandEntity.class))).thenReturn(brandDto1);

        BrandDto savedBrand = brandService.addBrand(brandDto1);

        assertNotNull(savedBrand);
        assertEquals("Marca1", savedBrand.getName());
        assertEquals(5, savedBrand.getWarranty());
        assertEquals("España", savedBrand.getCountry());
        verify(brandRepository, times(1)).save(any(BrandEntity.class));
    }

    @Test
    void getBrandById() {
        when(brandRepository.findById(1)).thenReturn(Optional.of(brandEntity1));
        when(brandMapper.toBrandDto(any(BrandEntity.class))).thenReturn(brandDto1);

        BrandDto foundBrand = brandService.getBrandById(1);

        assertNotNull(foundBrand);
        assertEquals("Marca1", foundBrand.getName());
        assertEquals(5, foundBrand.getWarranty());
        assertEquals("España", foundBrand.getCountry());

        when(brandRepository.findById(2)).thenReturn(Optional.empty());

        BrandDto notFoundBrand = brandService.getBrandById(2);

        assertNull(notFoundBrand);
    }

    @Test
    void updateBrand() {
        when(brandRepository.findById(1)).thenReturn(Optional.of(brandEntity1));
        when(brandMapper.toBrandEntity(any(BrandDto.class))).thenReturn(brandEntity1);

        brandService.updateBrand(1, brandDto1);

        verify(brandRepository, times(1)).save(any(BrandEntity.class));

        when(brandRepository.findById(2)).thenReturn(Optional.empty());

        brandService.updateBrand(2, brandDto2);

        verify(brandRepository, times(0)).save(brandEntity2);
    }

    @Test
    void deleteBrand() {
        doNothing().when(brandRepository).deleteById(1);

        brandService.deleteBrand(1);

        verify(brandRepository, times(1)).deleteById(1);
    }

    @Test
    void getAllBrands() throws Exception {
        when(brandRepository.findAll()).thenReturn(Arrays.asList(brandEntity1, brandEntity2));
        when(brandMapper.toBrandDto(brandEntity1)).thenReturn(brandDto1);
        when(brandMapper.toBrandDto(brandEntity2)).thenReturn(brandDto2);

        CompletableFuture<List<BrandDto>> brandsFuture = brandService.getAllBrands();
        List<BrandDto> brands = brandsFuture.get();

        assertNotNull(brands);
        assertEquals(2, brands.size());
        assertEquals("Marca1", brands.get(0).getName());
        assertEquals("Marca2", brands.get(1).getName());

        verify(brandRepository, times(1)).findAll();
    }
}
