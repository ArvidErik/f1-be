package com.example.demo.service;

import com.example.demo.model.DriverResult;
import com.example.demo.repository.DriverResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverResultService {

    @Autowired
    private DriverResultRepository driverResultRepository;

    public List<DriverResult> getAllItems() {
        return driverResultRepository.findAll();
    }

    public Optional<DriverResult> getItemById(String id) {
        return driverResultRepository.findById(id);
    }

}
