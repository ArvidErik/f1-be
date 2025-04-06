package com.example.demo;

import com.example.demo.model.DriverStanding;
import com.example.demo.repository.DriverStandingRepository;
import com.example.demo.service.ScraperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runOnStartup(ScraperService scraperService) {
		return args -> {
			System.out.println("🚀 Running initial scrape on startup...");
			scraperService.scrapeDriverStandings();
		};
	}
}
