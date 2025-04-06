package com.example.demo.controller;

import com.example.demo.model.DriverStanding;
import com.example.demo.service.DriverStandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/driverStanding")
public class DriverStandingController {

    @Autowired
    private DriverStandingService driverStandingService;

    @GetMapping
    public List<DriverStanding> getAllItems() {
        return driverStandingService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverStanding> getItemById(@PathVariable String id) {
        return driverStandingService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
