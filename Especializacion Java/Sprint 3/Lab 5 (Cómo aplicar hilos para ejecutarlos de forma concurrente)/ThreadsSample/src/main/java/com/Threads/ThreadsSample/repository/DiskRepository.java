package com.Threads.ThreadsSample.repository;

import com.Threads.ThreadsSample.repository.entity.DiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiskRepository extends JpaRepository<DiskEntity,Integer> {
}
