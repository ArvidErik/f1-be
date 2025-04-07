package com.example.demo.repository;

import com.example.demo.model.DriverResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DriverResultRepository extends MongoRepository<DriverResult, String> {
    List<DriverResult> findByName(String name);
}
