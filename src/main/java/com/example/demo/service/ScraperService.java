package com.example.demo.service;

import com.example.demo.model.DriverStanding;
import com.example.demo.repository.DriverStandingRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    @Autowired
    private DriverStandingRepository repository;

    public List<DriverStanding> scrapeDriverStandings() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        List<DriverStanding> standings = new ArrayList<>();

        try {
            driver.get("https://www.formula1.com/en/results/2025/drivers");
            List<WebElement> rows = driver.findElements(By.cssSelector(".f1-table tbody tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.cssSelector("td p"));

                DriverStanding ds = new DriverStanding();
                ds.setPosition(parseInt(cells, 0));
                ds.setName(getText(cells, 1));
                ds.setNationality(getText(cells, 2));
                ds.setTeam(getText(cells, 3));
                ds.setPoints(parseInt(cells, 4));

                standings.add(ds);
            }

            // Clear old data and insert fresh
            repository.deleteAll();
            repository.saveAll(standings);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return standings;
    }

    private int parseInt(List<WebElement> cells, int index) {
        try {
            return Integer.parseInt(cells.get(index).getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseDouble(List<WebElement> cells, int index) {
        try {
            return Double.parseDouble(cells.get(index).getText().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private String getText(List<WebElement> cells, int index) {
        try {
            return cells.get(index).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
