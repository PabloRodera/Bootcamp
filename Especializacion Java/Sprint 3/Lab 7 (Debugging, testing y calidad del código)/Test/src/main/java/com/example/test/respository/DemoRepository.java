package com.example.test.respository;

import com.example.test.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {
    DemoEntity findByName(String name);
}
