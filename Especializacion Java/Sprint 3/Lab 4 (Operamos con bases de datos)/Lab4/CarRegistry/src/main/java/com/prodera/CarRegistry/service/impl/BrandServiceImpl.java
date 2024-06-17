package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.BrandDto;
import com.prodera.CarRegistry.controller.mapper.BrandMapper;
import com.prodera.CarRegistry.repository.BrandRepository;
import com.prodera.CarRegistry.repository.entity.BrandEntity;
import com.prodera.CarRegistry.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BrandDto addBrand(BrandDto brandDto) {
        BrandEntity brandEntity = brandMapper.toBrandEntity(brandDto);
        BrandEntity savedBrandEntity = brandRepository.save(brandEntity);
        BrandDto savedBrandDto = brandMapper.toBrandDto(savedBrandEntity);

        log.info("Brand saved successfully: {}", savedBrandDto);
        return savedBrandDto;
    }
    @Override
    public BrandDto getBrandById(Integer id) {
        Optional<BrandEntity> brandEntity = brandRepository.findById(id);
        return brandEntity.map(brandMapper::toBrandDto).orElse(null);
    }
    @Override
    public void updateBrand(Integer id, BrandDto brandDto) {
        Optional<BrandEntity> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            BrandEntity brandEntity = brandMapper.toBrandEntity(brandDto);
            brandEntity.setId(id);
            brandRepository.save(brandEntity);
        } else {
            log.error("Brand with ID {} not found", id);
        }
    }

    @Override
    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        return brandEntities.stream().map(brandMapper::toBrandDto).collect(Collectors.toList());
    }


}