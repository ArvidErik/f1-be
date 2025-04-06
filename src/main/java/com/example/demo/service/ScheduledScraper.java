package com.example.demo.service;

import com.example.demo.service.ScraperService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledScraper {

    private final ScraperService scraperService;

    public ScheduledScraper(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @Scheduled(cron = "0 0 18 ? * SUN") // Every Sunday at 6 PM
    public void updateStandings() {
        System.out.println("📅 Running scheduled F1 standings scrape (Sunday 6PM)...");
        scraperService.scrapeDriverStandings();
    }
}

