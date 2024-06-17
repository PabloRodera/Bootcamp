package com.prodera.CarRegistry.repository;

import com.prodera.CarRegistry.repository.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity,Integer> {

}
