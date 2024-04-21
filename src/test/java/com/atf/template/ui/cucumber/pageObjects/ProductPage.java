package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.config.WebDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.atf.template.ui.cucumber.actions.GenericActions.checkIfPresent;
import static com.atf.template.ui.cucumber.actions.GenericActions.click;

@Slf4j
public class ProductPage extends BasePage{
    protected WebDriver driver;

    //TODO define radio button elements to validate selected state
    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[1]")
    protected WebElement oneTestRadioButton;
    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[2]")
    protected WebElement twoTestsRadioButton;
    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[3]")
    protected WebElement threeTestsRadioButton;

//    @FindBy(xpath = "//button[@class='counter__btn counter__btn--remove']")
    @FindBy(css = "#__next > div > div.wrapper__main > section.product-detail-section.section > div.container-fluid > div > div > div.product-detail__col--right > div.product-detail__details > div.product-detail__info > div.product-detail__controls > div > button.counter__btn.counter__btn--remove")
    protected WebElement productCounterRemoveButton;
//    @FindBy(xpath = "//button[@class='counter__btn counter__btn--add']")
    @FindBy(css = "#__next > div > div.wrapper__main > section.product-detail-section.section > div.container-fluid > div > div > div.product-detail__col--right > div.product-detail__details > div.product-detail__info > div.product-detail__controls > div > button.counter__btn.counter__btn--add")
    protected WebElement productCounterAddButton;
//    @FindBy(xpath = "//div/input[@class='counter__input input']")
    @FindBy(css = "div[class='counter product-detail__counter'] input[name='quantity']")
    protected WebElement productCounter;

    @FindBy(xpath = "//s[@class='product-detail__old-price']")
    protected WebElement oldPriceLabel;
    @FindBy(xpath = "//strong[@class='product-detail__new-price']")
    protected WebElement newPriceLabel;
    @FindBy(xpath = "//span[@class='product-detail__offertag offertag']")
    protected WebElement specialOfferLabel;

    public ProductPage(WebDriver driver) {
        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }
    public void selectTestsCount(String testsCount) {
        switch (testsCount) {
            case "1 test":
//                checkIfPresent(oneTestRadioButton);

                click(oneTestRadioButton);
                break;
            case "2 tests":
//                checkIfPresent(twoTestsRadioButton);
                click(twoTestsRadioButton);
                break;
            case "3 tests":
//                checkIfPresent(threeTestsRadioButton);
                click(threeTestsRadioButton);
                break;
            default:
                throw new NoSuchElementException("No such tests package option is available on page: " + testsCount + "test(s)");
        }
    }

    public int getProductCount() {
        checkIfPresent(productCounter);
        return Integer.parseInt(productCounter.getAttribute("value"));
    }

    public String getProductPrice() {
        checkIfPresent(newPriceLabel);
        return newPriceLabel.getText().replace("$", "");
    }

    public String getProductQuantity(String product, int testsCount) {
        String productTestsCount = "not_defined";
        // Define the regex pattern for numeric value followed by "test(s)"
        String regexPattern = "((\\d+) test(s?))";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regexPattern);

        switch (testsCount) {
            case 1:
                checkIfPresent(oneTestRadioButton);
                productTestsCount = oneTestRadioButton.getText();
                break;
            case 2:
                checkIfPresent(twoTestsRadioButton);
                productTestsCount = twoTestsRadioButton.getText();
                break;
            case 3:
                checkIfPresent(threeTestsRadioButton);
                productTestsCount = threeTestsRadioButton.getText();
                break;
            default:
                throw new java.util.NoSuchElementException("No such tests package option is available for product: " + testsCount + "test(s)");
        }
        // Match the pattern against the input string
        Matcher matcher = pattern.matcher(productTestsCount);

        if (matcher.find()) {
            // Extract the numeric value (group 1)
            log.info("Product Tests count in package option: {}", matcher.group(1));
            return matcher.group(1);
//            System.out.println("Found: " + numericValue + " test(s)");
        } else {
            return productTestsCount;
        }
    }

    public int clickAddProduct () {
        log.info("clickAddProduct method");
        checkIfPresent(productCounterAddButton);
        productCounterAddButton.click();
        log.info("Product count increased method");
        return getProductCount();
    }

    public int clickRemoveProduct () {
        log.info("clickRemoveProduct method");
//        log.info("IsEnabled: {}", productCounterRemoveButton.isEnabled());
//        log.info("isDisplayed: {}", productCounterRemoveButton.isDisplayed());
//        log.info("isSelected: {}", productCounterRemoveButton.isSelected());
//        log.info("getLocation: {}", productCounterRemoveButton.getLocation());
//        log.info("getLocation: {}", productCounterRemoveButton.getCssValue("class"));
//
//        Actions actions = new Actions(WebDriverConfig.getDriver());
////        actions.click(productCounterRemoveButton).build().perform();
////        WebElement myElement = new WebDriverWait(driver, Duration.ofMillis(20)).until(ExpectedConditions.visibilityOf(productCounterRemoveButton));
////        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
//        actions.moveToElement(productCounterRemoveButton).build().perform();
//
//        actions.click(productCounterRemoveButton).build().perform();

//        click(productCounterRemoveButton);
        checkIfPresent(productCounterRemoveButton);
        productCounterRemoveButton.click();
        log.info("Product count decreased method");
        return getProductCount();
    }
}
