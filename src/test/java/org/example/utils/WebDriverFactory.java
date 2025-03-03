package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String driver) {
        switch (driver.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();

            case "edge":
                return new EdgeDriver();

            case "firefox":
                return new FirefoxDriver();

            default:
                throw new IllegalArgumentException("Unknown driver: " + driver);
        }
    }
}
