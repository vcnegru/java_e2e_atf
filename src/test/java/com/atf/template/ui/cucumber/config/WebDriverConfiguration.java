package com.atf.template.ui.cucumber.config;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.atf.template.ui.cucumber.config.PropertiesKeys.HEADLESS;
import static com.atf.template.ui.cucumber.config.PropertyReader.getProperty;
import static java.lang.Boolean.parseBoolean;

@Slf4j
public class WebDriverConfiguration {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            log.info("Starting NEW WebDriver!!!");
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            if (parseBoolean(getProperty(HEADLESS))) {
                chromeOptions.addArguments("--headless");
                log.info("ChromeDriver started in headless mode");
            }
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }
}
