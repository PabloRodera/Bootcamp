package com.prodera.CarRegistry.service;

import com.prodera.CarRegistry.controller.dto.BrandDto;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BrandService {

    BrandDto addBrand(BrandDto brandDto);

    BrandDto getBrandById(Integer id);

    void updateBrand(Integer id, BrandDto brandDto);

    void deleteBrand(Integer id);

    CompletableFuture<List<BrandDto>> getAllBrands();
}
