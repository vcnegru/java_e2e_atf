package com.atf.template.ui.cucumber.actions;

import com.atf.template.ui.cucumber.config.WebDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import static com.atf.template.ui.cucumber.config.WebDriverConfiguration.getDriver;
import static com.atf.template.ui.cucumber.config.WebDriverConfig.getDriver;
@Slf4j
public class GenericActions {

    public static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));

    public static void click(WebElement element) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public static String getText(WebElement element) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getSomething(WebElement element, String attribute) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);
    }

    public static void populateField(WebElement element, String value) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
        log.info("Populated field with {}", value);
    }

    public static void selectFromDropdown(WebElement element, String value) {
        waitFluent().until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(value);
        log.info("Selected from dropdown value: {}", value);
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
    public static void moveToWithJS(WebElement webElement) {
        Actions actions = new Actions(WebDriverConfig.getDriver());
        actions.scrollToElement(webElement).build().perform();
        actions.moveToElement(webElement).build().perform();
    }

    public static int extractIndexVariable(String input) {
        String pattern = "\\[(\\d+)]"; // Regular expression to match the index variable
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        if (m.find()) {
            return Integer.parseInt(m.group(1)); // Extract the index variable
        }
        return -1; // Return -1 if no index variable found
    }

    public static String extractPropertyVariable(String input) {
        String pattern = "\\[\\d+]\\.(.*)"; // Regular expression to match the index variable
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        if (m.find()) {
            return m.group(1); // Extract the index variable
        }
        return "property_not_extracted"; // Return default value if no name extracted for variable found
    }
}
