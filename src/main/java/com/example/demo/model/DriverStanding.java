package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "driver_standings")
public class DriverStanding {
    @Id
    private String id;
    private Integer position;
    private String name;
    private String nationality;
    private String team;
    private Integer points;
}
