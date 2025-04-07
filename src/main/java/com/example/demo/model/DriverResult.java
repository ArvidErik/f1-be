package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "driver_result")
public class DriverResult {
    @Id
    private String id;
    private Integer position;
    private String driver;
    private String team;
    private Integer points;
}
