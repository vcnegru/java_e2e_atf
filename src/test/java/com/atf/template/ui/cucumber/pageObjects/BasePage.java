package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.config.WebDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.config.WebDriverConfig.getDriver;
@Slf4j
public class BasePage {

    protected WebDriver driver;

    // Other Products Web Elements
    //Breed + Health Cat DNA Test
    //Oral Health Test for Cats
    //Whole Genome Test
    //Breed + Health Dog DNA Test

    //  Mini Cart----------------------------------------------
    @FindBy(xpath = "//div[@class='mini-cart__title']")
    private WebElement miniCartTitle;
    @FindBy(xpath = "//button[@class='mini-cart__close']")
    private WebElement miniCartCloseButton;
    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
    private WebElement miniCartContinueShoppingButton;
    @FindBy(xpath = "//button[@aria-label='Show minicart']")
    private WebElement topMenuMiniCart;
    @FindBy(xpath = "//div[@class='header-controls__cart-count cart-count']")
    private WebElement cartCount;
    public void clickCloseMiniCart() {
        click(miniCartCloseButton);
    }
    public BasePage(WebDriver driver) {
        this.driver = WebDriverConfig.getDriver();
        PageFactory.initElements(WebDriverConfig.getDriver(), this);
    }
    public String getTextBase(String element) {
        String text = "element_has_no_text";
        switch (element) {
            case "cartCount": text = cartCount.getText();
        }
        return  text;
    }

    public String getText(WebElement webElement) {
//        checkIfPresent(webElement);
        return  webElement.getText();
    }


}
