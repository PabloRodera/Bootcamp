package com.prodera.CarRegistry.controller.mapper;

import com.prodera.CarRegistry.repository.entity.BrandEntity;
import com.prodera.CarRegistry.controller.dto.BrandDto;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandDto toBrandDto(BrandEntity brandEntity) {
        if (brandEntity == null) {
            return null;
        }

        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandEntity.getId());
        brandDto.setName(brandEntity.getName());
        brandDto.setWarranty(brandEntity.getWarranty());
        brandDto.setCountry(brandEntity.getCountry());
        return brandDto;
    }

    public BrandEntity toBrandEntity(BrandDto brandDto) {
        if (brandDto == null) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandDto.getId());
        brandEntity.setName(brandDto.getName());
        brandEntity.setWarranty(brandDto.getWarranty());
        brandEntity.setCountry(brandDto.getCountry());
        return brandEntity;
    }
}
