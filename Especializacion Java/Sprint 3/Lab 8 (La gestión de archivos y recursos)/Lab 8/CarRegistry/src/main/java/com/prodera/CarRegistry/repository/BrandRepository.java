package com.prodera.CarRegistry.repository;

import com.prodera.CarRegistry.repository.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    Optional<BrandEntity> findByName(String name);
}
