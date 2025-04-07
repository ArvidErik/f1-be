package com.example.demo.controller;

import com.example.demo.model.DriverResult;
import com.example.demo.model.DriverStanding;
import com.example.demo.service.DriverResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/driverResult")
public class DriverResultController {

    @Autowired
    private DriverResultService driverResultService;

    @GetMapping
    public List<DriverResult> getAllItems() { return driverResultService.getAllItems(); }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResult> getItemById(@PathVariable String id) {
        return driverResultService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
