package com.example.demo.service;

import com.example.demo.model.DriverStanding;
import com.example.demo.repository.DriverStandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverStandingService {
    @Autowired
    private DriverStandingRepository driverStandingRepository;

    public List<DriverStanding> getAllItems() {

        return driverStandingRepository.findAll().stream()
                .sorted((d1, d2) -> Integer.compare(d2.getPoints(), d1.getPoints()))
                .toList();
    }

    public Optional<DriverStanding> getItemById(String id) {
        return driverStandingRepository.findById(id);
    }
}
