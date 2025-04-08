package com.example.demo.repository;

import com.example.demo.model.DriverResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverResultRepository extends MongoRepository<DriverResult, String> {
    Optional<DriverResult> findById(String id);
}
