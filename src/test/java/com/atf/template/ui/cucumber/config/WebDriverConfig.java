package com.atf.template.ui.cucumber.config;

import com.atf.template.ui.cucumber.context.MiniCart;
import com.atf.template.ui.cucumber.context.ScenarioContext;
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
public class WebDriverConfig {
    private static WebDriver driver;

    @Before
    public void setUp() {
        // Initialize WebDriver before each scenario
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--window-size=1920,1080");
        if (parseBoolean(getProperty(HEADLESS))) {
            chromeOptions.addArguments("--headless=new");
            log.info("ChromeDriver started in headless mode");
        }
        driver = new ChromeDriver(chromeOptions);
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        // Quit WebDriver after each scenario
        ScenarioContext.resetContext();
        MiniCart.resetMiniCartContext();
        if (driver != null) {
            driver.quit();
//            driver.close();
        }
    }
    public static WebDriver getDriver() {
        return driver;
    }
}
