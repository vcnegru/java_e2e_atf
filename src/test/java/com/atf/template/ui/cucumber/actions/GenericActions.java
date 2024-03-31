package com.atf.template.ui.cucumber.actions;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import static com.atf.template.ui.cucumber.config.WebDriverConfiguration.getDriver;
@Slf4j
public class GenericActions {

    public static void click(WebElement element) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        click(element);
    }

    public static void populateField(WebElement element, String value) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
        log.info("Populated field with {}", value);
    }

    public static Boolean checkIfPresent(WebElement element) {
        try {
            waitFluent().until(ExpectedConditions.visibilityOf(element));
            log.info("Element name '{}' is present on the page (element='{}')", element.getText(), element.toString());
        } catch (TimeoutException exception) {
            log.error("Element is NOT present on the page: {}", element.toString());
            return false;
        }
        return true;
    }

    public static FluentWait<WebDriver> waitFluent() {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

}
