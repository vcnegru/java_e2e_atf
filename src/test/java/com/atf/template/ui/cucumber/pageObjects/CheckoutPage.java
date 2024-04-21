package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.helper.Product;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.data.DataActions.*;

@Slf4j
public class CheckoutPage extends BasePage {

    //    private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);
    private WebDriver driver;

    // Express Checkout Header
    @FindBy(xpath = "//h2[normalize-space()='Express checkout']")
    private WebElement checkoutHeader;
    // Contact
    @FindBy(xpath = "//input[@id='email']") // aria-describedby="error-for-email"
    private WebElement email;
    @FindBy(xpath = "//span/p[@id='error-for-email']")
    private WebElement emailError;
    // Delivery
    @FindBy(xpath = "//input[@name='firstName']") // attribute aria-describedby="error-for-TextField1"
    private WebElement firstName;
    @FindBy(xpath = "//span/p[@id='error-for-TextField1']")
    private WebElement firstNameError;
    @FindBy(xpath = "//input[@name='lastName']") // attribute aria-describedby="error-for-TextField2"
    private WebElement lastName;
    @FindBy(xpath = "//span/p[@id='error-for-TextField2']")
    private WebElement lastNameError;
    @FindBy(xpath = "//input[@name='address1']")  // attribute aria-describedby="error-for-TextField3"
    private WebElement address;
    @FindBy(xpath = "//span/p[@id='error-for-TextField3']")
    private WebElement addressError;
    @FindBy(xpath = "//input[@name='city']")  // attribute aria-describedby="error-for-TextField9"
    private WebElement city;
    @FindBy(xpath = "//span/p[@id='error-for-TextField9']")
    private WebElement cityError;
    @FindBy(xpath = "//select[@name='zone']") //attribute aria-describedby="error-for-Select1"
    private WebElement state;
    @FindBy(xpath = "//span/p[@id='error-for-Select1']")
    private WebElement stateError;
    @FindBy(xpath = "//input[@name='postalCode']")  // attribute aria-describedby="error-for-TextField10"
    private WebElement postalCode;
    @FindBy(xpath = "//span/p[@id='error-for-TextField10']")
    private WebElement postalCodeError;
// Shipping Method

    // Payment method
    @FindBy(xpath = "//iframe[contains(@id,'card-fields-number')]")
    public WebElement iframe;
    @FindBy(xpath = "//input[@name='number']") // aria-describedby="error-for-number tooltip-for-number"
    private WebElement cardNumber;
//    @FindBy(xpath = "//span/p[@id='error-for-number']")
//    private WebElement cardNumberError;
    @FindBy(xpath = "//input[@name='expiry']") // aria-describedby="error-for-expiry tooltip-for-expiry"
    private WebElement expDate;
//    @FindBy(xpath = "//span/p[@id='error-for-expiry']")
//    private WebElement expDateError;
    @FindBy(xpath = "//input[@name='verification_value']") // aria-describedby="error-for-verification_value tooltip-for-verification_value"
    private WebElement cvv;
//    @FindBy(xpath = "//span/p[@id='error-for-verification_value']")
//    private WebElement cvvError;
    @FindBy(xpath = "//input[@name='name']") // aria-describedby="error-for-name tooltip-for-name"
    private WebElement nameOnCard;
//    @FindBy(xpath = "//span/p[@id='error-for-name']")
//    private WebElement nameOnCardError;
// Remember me


    // Pay now BUTTON
    @FindBy(xpath = "//button[@class='QT4by _1fragemjk rqC98 _1m2hr9gc _1m2hr9ga _7QHNJ VDIfJ j6D1f janiy']")
    private WebElement payNowButton;
// aside - Order Details
// products and details

    @FindBy(xpath = PRODUCT_DESCRIPTION_XPATH)
    private WebElement productDescription;
    @FindBy(xpath = PRODUCT_COUNT_XPATH)
    private WebElement productCount;
    @FindBy(xpath = PRODUCT_SUBTOTAL_XPATH + "/p")
    private WebElement productSubtotalP;
    @FindBy(xpath = PRODUCT_SUBTOTAL_XPATH + "/span")
    private WebElement productSubtotalSpan;

    // Discount Code or Gift Card
    @FindBy(xpath = "//input[@id='ReductionsInput0']")
    private WebElement discountCodeInput;
    @FindBy(xpath = "//button[@aria-label='Apply Discount Code']")
    private WebElement discountCodeApplyButton;

    // Subtotal / Shipping / Total
    @FindBy(xpath = "//span[@class='_19gi7yt0 _19gi7ytl _1fragemp3 _19gi7yt1 notranslate']")
    private WebElement subtotal;
    @FindBy(xpath = "//span[@class='_19gi7yt0 _19gi7ytl _1fragemp2 _19gi7ytf _19gi7ytb notranslate']")
    private WebElement shipping;
    @FindBy(xpath = "//strong[@class='_19gi7yt0 _19gi7ytr _1fragemp5 _19gi7yt1 notranslate']")
    private WebElement total;
    @FindBy(xpath = "//strong[@class='_19gi7yt0 _19gi7ytl _1fragemp3 _19gi7yt1 notranslate']")
    private WebElement totalSavings;
    // Total Savings
    @FindBy(xpath = "//div[@role='row']//div[@role='rowheader']//strong[1]")
    private WebElement totalSavingsHeader;
    @FindBy(xpath = "//div[@role='row']//div[@role='rowheader']//strong[2]")
    private WebElement totalSavingsValue;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getExpressCheckoutHeaderText() {
        return checkoutHeader.getText();
    }

    public String getSubtotal() {
        return getText(subtotal).replace("$", "");
    }

    public String getShipping() {
        return getText(shipping);
    }

    public String getTotal() {
        return getText(total).replace("$", "");
    }

    public String getTotalSavings() {
        return getText(totalSavings);
    }

    public void clickDiscountCodeApplyButton() {
        checkIfPresent(discountCodeApplyButton);
        discountCodeApplyButton.click();
        log.info("Click Apply Discount Code.");
    }

    public void populateDiscountCodeInput(String discountCode) {
        populateField(discountCodeInput, discountCode);
    }

    public void clearDiscountCodeInput() {
        populateField(discountCodeInput, "");
    }

    public void clickPayNowButton() {
        click(payNowButton);
    }

    public String getErrorFieldText(String field) {
        switch (field) {
            case "email": return getText(emailError);
            case "firstName": return getText(firstNameError);
            case "lastName": return getText(lastNameError);
            case "address": return getText(addressError);
            case "city": return getText(cityError);
            case "state": return getText(stateError);
            case "zip": return getText(postalCodeError);
//            case "cardNumber": return getText(cardNumberError);
//            case "expirationDate": return getText(expDateError);
//            case "cvv": return getText(cvvError);
//            case "nameOnCard": return getText(nameOnCardError);
        }
        return "Element Error not found";
    }

    public void populateFieldText(String field, String value) {
        switch (field) {
            case "email": populateField(email, value); break;
            case "firstName": populateField(firstName, value); break;
            case "lastName": populateField(lastName, value); break;
            case "address": populateField(address, value); break;
            case "city": populateField(city, value); break;
            case "state": selectFromDropdown(state, value); break;
            case "zip": populateField(postalCode, value); break;
            case "cardNumber": populateField(cardNumber, value); break;
            case "expirationDate": populateField(expDate, value); break;
            case "cvv": populateField(cvv, value); break;
            case "nameOnCard": populateField(nameOnCard, value); break;
        }
    }

    public String getAttributeForFieldWithError(String field) {
        String attribute = "aria-describedby";
        switch (field) {
            case "email": return email.getAttribute(attribute);
            case "firstName": return firstName.getAttribute(attribute);
            case "lastName": return lastName.getAttribute(attribute);
            case "address": return address.getAttribute(attribute);
            case "city": return city.getAttribute(attribute);
            case "state": return state.getAttribute(attribute);
            case "zip": return postalCode.getAttribute(attribute);
            case "cardNumber": return cardNumber.getAttribute(attribute);
            case "expirationDate": return expDate.getAttribute(attribute);
            case "cvv": return cvv.getAttribute(attribute);
            case "nameOnCard": return nameOnCard.getAttribute(attribute);
        }
        return "Element Error not found";
    }

    public void checkIfApplyDiscountButtonIsDisplayed() {
        checkIfPresent(discountCodeApplyButton);
    }
}
