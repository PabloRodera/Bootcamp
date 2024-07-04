package com.prodera.CarRegistry.repository;

import com.prodera.CarRegistry.repository.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
}
