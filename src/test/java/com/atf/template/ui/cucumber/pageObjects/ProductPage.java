package com.atf.template.ui.cucumber.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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

    @FindBy(xpath = "//button[@class='counter__btn counter__btn--remove']")
    protected WebElement productCounterRemoveButton;
    @FindBy(xpath = "//button[@class='counter__btn counter__btn--add']")
    protected WebElement productCounterAddButton;
    @FindBy(xpath = "//div/input[@class='counter__input input']")
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
        productCounterAddButton.click();
        return getProductCount();
    }

    public int clickRemoveProduct () {
        click(productCounterRemoveButton);
//        productCounterRemoveButton.click();
        return getProductCount();
    }
}
