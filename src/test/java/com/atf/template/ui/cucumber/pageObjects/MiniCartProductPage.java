package com.atf.template.ui.cucumber.pageObjects;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;

@Slf4j
@Getter
public class MiniCartProductPage extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='mini-cart__title']")
    private WebElement miniCartTitle;
    @FindBy(xpath = "//div[@class='cart-product__product']")
    private List<WebElement> listOfProducts;
    @FindBy(xpath = "//a/img[@class='cart-product__img']")
    private WebElement productImage;
    @FindBy(xpath = "//div[@class='cart-product__title']")
    private WebElement cartProductName;
    @FindBy(xpath = "//div[@class='cart-product__quantity']")
    private WebElement cartProductQuantity;
    @FindBy(xpath = "//button[@class='cart-product__cart-btn btn btn--gray-reverse btn--xs']")
    private WebElement cartProductRemoveButton;
    @FindBy(xpath = "//div[@class='cart-product__controls']//button[@class='counter__btn counter__btn--remove']")
    private WebElement productQuantityDecreaseButton;
    @FindBy(xpath = "//div[@class='cart-product__controls']/div/input")
    private WebElement productQuantityInfoLabel;
    @FindBy(xpath = "//div[@class='cart-product__controls']//button[@class='counter__btn counter__btn--add']")
    private WebElement productQuantityIncreaseButton;
    @FindBy(xpath = ".//div[@class='cart-product__prices total-price']//div[@class='total-price__new-price']")
    private WebElement productTotalPrice;
    @FindBy(xpath = "//div[@class='summary__row']//div[@class='summary__info']")
    private WebElement summaryInfo;
    @FindBy(xpath = "//div[@class='summary__row']//div[@class='total-price__new-price']")
    private WebElement summaryTotalPrice;

    @FindBy(xpath = "//div/a[@class='summary__btn btn btn--primary ']")
    private WebElement miniCartCheckoutButton;
    @FindBy(xpath = "//div/a[@class='summary__btn btn btn--purple-reverse']")
    private WebElement viewCartButton;

    public MiniCartProductPage(WebDriver driver) {
        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }



    public List<WebElement> returnProductsMiniCart() {
        return listOfProducts;
    }

    public double getTotalPrice() {
        return Double.parseDouble(summaryTotalPrice.getText().replace("$", ""));
    }

    public String getTotalQuantity() {
        return summaryInfo.getText();
    }

    public String getMiniCartHeaderTitle() {
        return miniCartTitle.getText();
    }

    public boolean checkMiniCartHeaderIsDisplayed() {
        return checkIfPresent(miniCartTitle);
    }

//    public static Boolean checkIfPresent(WebElement element) {
//        try {
//            waitFluent().until(ExpectedConditions.visibilityOf(element));
//            log.info("Element name '{}' is present on the page (element='{}')", element.getText(), element.toString());
//        } catch (TimeoutException exception) {
//            log.error("Element is NOT present on the page: {}", element.toString());
//            return false;
//        }
//        return true;
//    }

    public void clickMiniCartCheckoutButton() {
        click(miniCartCheckoutButton);
    }

    public void clickViewCartButton() {
        click(viewCartButton);
    }
}
