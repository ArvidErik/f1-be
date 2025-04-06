package com.example.demo.repository;

import com.example.demo.model.DriverStanding;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DriverStandingRepository extends MongoRepository<DriverStanding, String> {
    List<DriverStanding> findByName(String name);
}
