package com.atf.template.ui.cucumber.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.actions.GenericActions.populateField;
@Slf4j
public class CartPage extends HomePage{

    // Cart Header
    @FindBy(xpath = "//h3")
    private WebElement cartHeader;

    // Cart Products Table
    @FindBy(xpath = "//div[@class='cart__row']")
    private List<WebElement> productsList;
    @FindBy(xpath = "//div[@class='cart-product__info']/a[@class='cart-product__title']")
    private WebElement productDescription;
    @FindBy(xpath = "//div[@class='cart-product__info']/p[@class='cart-product__quantity']")
    private WebElement testSets;
    @FindBy(xpath = "//div[@class='cart-product__info']//div[@class='coupon__text']")
    private WebElement discount;
    @FindBy(xpath = "//button[@class='cart-product__cart-btn btn btn--gray-reverse btn--xs']")
    private WebElement removeFromCartButton;
    @FindBy(xpath = "//div[@class='counter cart-product__counter']/button[@data-action='add']")
    private WebElement productCountIncrease;
    @FindBy(xpath = "//div[@class='counter cart-product__counter']/button[@data-action='remove']")
    private WebElement productCountDecrease;
    @FindBy(xpath = "//div[@class='counter cart-product__counter']/input")
    private WebElement productCount;
    @FindBy(xpath = "//div[@class='cart-product__prices total-price']//div[@class='total-price__new-price']")
    private WebElement productSubtotal;
    @FindBy(xpath = "//div[@class='cart-product__prices total-price']//div[@class='total-price__old-price']")
    private WebElement productSubtotalOld;
    // Cart Discount Section
    @FindBy(xpath = "//label[@class='coupon-form__title']")
    private WebElement discountCodeHeader;
    @FindBy(xpath = "//input[@class='coupon-form__input']")
    private WebElement discountCodeInput;
    @FindBy(xpath = "(//button[normalize-space()='Apply'])[1]")
    private WebElement discountCodeApplyButton;
    @FindBy(xpath = "//div[@class='coupon-form__coupon coupon']//div[@class='coupon__text']")
    private WebElement couponText;
    @FindBy(xpath = "//a[@class='coupon__close-wrap']")
    private WebElement couponRemove;
    // Cart Total Section
    @FindBy(xpath = "//div[@class='coupon-area__summary summary']//div[@class='summary__row']/p[2]")
    private WebElement shippingValue;
    @FindBy(xpath = "//div[@class='coupon-area__summary summary']//div[@class='summary__row']/span[2]")
    private WebElement totalSavings;
    @FindBy(xpath = "//div[@class='coupon-area__summary summary']//div[@class='total-price__new-price']")
    private WebElement subtotal;
    // Cart Checkout Button
    @FindBy(xpath = "//a[@class='coupon-area__btn btn btn--primary ']")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartHeader() {
        return getText(cartHeader);
    }
    public String getCouponText() {
        return getText(couponText).replace("$","");
    }
    public void removeCoupon() {
        click(couponRemove);
    }

    public String getShippingConditions() {
        return getText(shippingValue);
    }
    public String getSubtotal() {
        moveToWithJS(subtotal);
//        WebElement element = driver.findElement(By.xpath("//div[@class='summary__summary-info']//div[@class='summary__row'][3]//div[@class='total-price__new-price']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element).perform();
        return getText(subtotal).replace("$", "");
    }

    public void clickDiscountCodeApplyButton() {
        discountCodeApplyButton.click();
        checkIfPresent(discountCodeApplyButton);
        log.info("Click Apply Discount Code.");
    }

    public void populateDiscountCodeInput(String discountCode) {
        populateField(discountCodeInput, discountCode);
    }

    public void clearDiscountCodeInput() {
        populateField(discountCodeInput, "");
    }
}
