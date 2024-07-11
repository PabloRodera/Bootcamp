package com.example.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "demo")
@Data
@Entity
public class DemoEntity {
    private String name;
}
