package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.config.PropertiesKeys;
import com.atf.template.ui.cucumber.helper.Product;
import com.atf.template.ui.cucumber.pageObjects.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.actions.GenericActions.decimalFormat;
import static com.atf.template.ui.cucumber.assertions.CustomAssertions.assertThat;
import static com.atf.template.ui.cucumber.config.PropertyReader.getProperty;
import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;
import static com.atf.template.ui.cucumber.data.DataActions.*;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.*;

public class CheckoutPageSteps extends BasePageSteps {

    public final static String EXPRESS_CHECKOUT_HEADER_TEXT = "Express checkout";
    private static final Logger log = LoggerFactory.getLogger(CheckoutPageSteps.class);

    @When("^user applies 'Discount code' on Checkout Page$")
    public void applyDiscountCodeCheckoutPage() {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);
        String discountCode = getProperty(PropertiesKeys.DISCOUNT_CODE);
        checkoutPage.populateDiscountCodeInput(discountCode);
        checkoutPage.clickDiscountCodeApplyButton();
    }

    @And("^Checkout Page is displayed with order details:$")
    public void checkoutPageIsDisplayedWithDetails(Map<String, String> checkoutDetails) throws InterruptedException {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);
        assertThat(format(" Checkout page header is displayed: %s", EXPRESS_CHECKOUT_HEADER_TEXT), checkoutPage.getExpressCheckoutHeaderText(), is(EXPRESS_CHECKOUT_HEADER_TEXT));

        List<Product> actualCheckoutProducts = parseCheckOutPageProducts();
        for (Map.Entry<String, String> expectedEntry : checkoutDetails.entrySet()) {
            int index = extractIndexVariable(expectedEntry.getKey());
            String property = extractPropertyVariable(expectedEntry.getKey());
            if ("name".equals(property)) {
                String actualName = actualCheckoutProducts.get(index).getProductDescription();
                assertThat(format("product[%s].name is", index), actualName, is(expectedEntry.getValue()));
            } else if ("quantity".equals(property)) {
                String actualQuantity = actualCheckoutProducts.get(index).getTestSetCount();
                assertThat(format("product[%s].quantity is", index), actualQuantity, is(expectedEntry.getValue()));
            } else if ("count".equals(property)) {
                int actualCount = actualCheckoutProducts.get(index).getProductCount();
                assertThat(format("product[%s].count is", index), actualCount, is(Integer.parseInt(expectedEntry.getValue())));
            } else if ("subtotal".equals(property)) {
                sleep(5000);
                checkoutPage.checkIfApplyDiscountButtonIsDisplayed();
                String actualSubtotal = decimalFormat.format(actualCheckoutProducts.get(index).getProductSubtotalPrice());
                assertThat(format("product[%s].subtotal is", index), actualSubtotal, is(expectedEntry.getValue()));
            }
        }
        log.info("End Step checkoutPageIsDisplayedWithDetails");
    }

    public ArrayList<Product> parseCheckOutPageProducts() {
        ArrayList<Product> checkoutProductsActual = new ArrayList<>();

        WebElement productsTableDiv = driver.findElement(By.cssSelector("div[role='table']"));
        if (productsTableDiv != null) {
            List<WebElement> rows = productsTableDiv.findElements(By.cssSelector("div[role='row']"));
            // Iterate through each row
            for (int i = 1; i < rows.size(); i++) {
                List<WebElement> cells = rows.get(i).findElements(By.cssSelector("div[role='cell']"));
                String productDescription = "";
                int productCount = 0;
                String testSetCount = "";
                double productSubtotalPrice = 0.0;
                // Iterate through each cell
                for (int j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            productCount = Integer.valueOf(cells.get(j).findElement(By.xpath(PRODUCT_COUNT_XPATH)).getText());
                            break;
                        case 1:
                            productDescription = cells.get(j).findElement(By.xpath(PRODUCT_DESCRIPTION_XPATH)).getText();
                            testSetCount = cells.get(j).findElement(By.xpath(PRODUCT_TESTS_SET_XPATH)).getText();
                            break;
                        case 2:
                            break; // do nothing, cell with hidden content - products count
                        case 3:
                            try{
                                sleep(2000);
                                productSubtotalPrice =Double.valueOf(cells.get(j).findElement(By.xpath(PRODUCT_SUBTOTAL_XPATH )).getText().replace("$", ""));
                            }
                            catch(Exception exception){
                                throw new NoSuchElementException("WebElement for product subtotal NOT Found on Checkout Page for index: " + j, exception);
                            }
                            break;
                    }
                    // Print the text of each cell
                    System.out.print(cells.get(j).getText() + "\t");
                }
                Product product = new Product(productDescription, testSetCount, productCount, productSubtotalPrice);
                checkoutProductsActual.add(product);
            }
        }
        return checkoutProductsActual;
    }

    @And("^Checkout Page is displayed with total section details:$")
    public void checkoutPageIsDisplayedWithRTotalSectionDetails(Map<String, String> checkoutTotalDetails) {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);
        assertThat("total price is: ", checkoutPage.getSubtotal(), is(checkoutTotalDetails.get("subtotal")));
//        assertThat("shipping conditions are: ", checkoutTotalDetails.get("shipping"), is(checkoutPage.getShipping()));
        assertThat("total price is: ", checkoutPage.getSubtotal(), is(checkoutTotalDetails.get("total")));
    }

    @And("^customer clicks on 'Pay Now' button$")
    public void clickPayNowButton() {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);
        checkoutPage.clickPayNowButton();
    }

    @And("^on Checkout Page errors are displayed for required fields:$")
    public void verifyCheckoutPageErrorsOnMandatoryFields(Map<String, String> expectedErrors) {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);

        for (Map.Entry<String, String> expectedEntry : expectedErrors.entrySet()) {
            switch (expectedEntry.getKey()) {
                case "email":
                    assertThat("Email error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("Email field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "firstName":
                    assertThat("First name error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("First name field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "lastName":
                    assertThat("Last name error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("Last name field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "address":
                    assertThat("Address error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("Address field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "city":
                    assertThat("City error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("City field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "state":
                    assertThat("State error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("State field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "zip":
                    assertThat("Zip code error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
                    assertThat("Zip code field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
            }
        }
    }

    @And("^in Card Number section errors are displayed for required fields:$")
    public void verifyCardSectionErrorsOnMandatoryFields(Map<String, String> expectedErrors) {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);
        checkIfPresent(checkoutPage.iframe);

        List<WebElement> iframesList = driver.findElements(By.xpath("//iframe"));

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
//        log.info("iframe is: %s", ExpectedConditions.frameToBeAvailableAndSwitchToIt(checkoutPage.iframe));
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(checkoutPage.iframe));

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'card-fields-number')]")));
        WebElement iframeCardNumber = driver.findElement(By.xpath("//input[@name='number']"));
        WebElement iframeExpDate = driver.findElement(By.xpath("//input[@name='expiry']"));
        WebElement iframeCVV = driver.findElement(By.xpath("//input[@name='verification_value']"));
        WebElement iframeNameOnCard = driver.findElement(By.xpath("//input[@name='name']"));

//        WebElement iframeCardNumberError = driver.findElement(By.xpath("//span/p[@id='error-for-number']"));
//        WebElement iframeExpDateError = driver.findElement(By.xpath("//span/p[@id='error-for-expiry']"));
//        WebElement iframeCVVError = driver.findElement(By.xpath("//span/p[@id='error-for-verification_value']"));
//        WebElement iframeNameOnCardError = driver.findElement(By.xpath("//span/p[@id='error-for-name']"));

        for (Map.Entry<String, String> expectedEntry : expectedErrors.entrySet()) {
            switch (expectedEntry.getKey()) {
                case "cardNumber":
                    driver.findElement(By.xpath("//input[@name='number']")).sendKeys("4444444444444448");
//                    iframeCardNumber.sendKeys("4444444444444448");
//                    assertThat("Card number code error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("Crad number field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "expirationDate":
                    driver.findElement(By.xpath("//input[@name='expiry']")).sendKeys("1227");
//                iframeExpDate.sendKeys("1227");
//                    assertThat("Exp date error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("Exp date field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "cvv":
                    iframeCVV.sendKeys("123");
//                    assertThat("CVV code error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("CVV code field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "nameOnCard":
                    iframeNameOnCard.sendKeys("JOHN DOE");
//                    assertThat("Name on card error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("Name on card field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
            }
        }
        driver.switchTo().defaultContent();
    }

    @And("^Card Number section is populated with fields:$")
    public void populateCardSectionFields(Map<String, String> expectedErrors) {
        CheckoutPage checkoutPage = (CheckoutPage) getFromContext(PAGE);
        checkIfPresent(checkoutPage.iframe);

        List<WebElement> iframesList = driver.findElements(By.xpath("//iframe"));

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
//        log.info("iframe is: %s", ExpectedConditions.frameToBeAvailableAndSwitchToIt(checkoutPage.iframe));
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(checkoutPage.iframe));

//        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'card-fields-number')]")));
//        WebElement iframeCardNumber = driver.findElement(By.xpath("//input[@name='number']"));
//        WebElement iframeExpDate = driver.findElement(By.xpath("//input[@name='expiry']"));
//        WebElement iframeCVV = driver.findElement(By.xpath("//input[@name='verification_value']"));
//        WebElement iframeNameOnCard = driver.findElement(By.xpath("//input[@name='name']"));

//        WebElement iframeCardNumberError = driver.findElement(By.xpath("//span/p[@id='error-for-number']"));
//        WebElement iframeExpDateError = driver.findElement(By.xpath("//span/p[@id='error-for-expiry']"));
//        WebElement iframeCVVError = driver.findElement(By.xpath("//span/p[@id='error-for-verification_value']"));
//        WebElement iframeNameOnCardError = driver.findElement(By.xpath("//span/p[@id='error-for-name']"));

        for (Map.Entry<String, String> expectedEntry : expectedErrors.entrySet()) {
            switch (expectedEntry.getKey()) {
                case "cardNumber":
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'card-fields-number')]")));
                    driver.findElement(By.xpath("//input[@name='number']")).sendKeys("4444444444444448");
                    driver.switchTo().defaultContent();
//                    iframeCardNumber.sendKeys("4444444444444448");
//                    assertThat("Card number code error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("Crad number field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "expirationDate":
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'card-fields-number')]")));
                    driver.findElement(By.xpath("//input[@id='expiry']")).sendKeys("1227");
                    driver.switchTo().defaultContent();
//                iframeExpDate.sendKeys("1227");
//                    assertThat("Exp date error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("Exp date field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "cvv":
//                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'card-fields-number')]")));
                    driver.findElement(By.xpath("//*[@id='verification_value']")).click();
                    driver.findElement(By.xpath("//*[@id='verification_value']")).sendKeys("123");
//                    driver.switchTo().defaultContent();
//                    assertThat("CVV code error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("CVV code field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
                case "nameOnCard":
//                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'card-fields-number')]")));
                    driver.findElement(By.xpath("//*[@id='name']")).clear();
                    driver.findElement(By.xpath("//*[@id='name']")).sendKeys("JOHN DOE");
//                    driver.switchTo().defaultContent();
//                    assertThat("Name on card error is displayed", checkoutPage.getErrorFieldText(expectedEntry.getKey()), is(expectedEntry.getValue()));
//                    assertThat("Name on card field has style with error", checkoutPage.getAttributeForFieldWithError(expectedEntry.getKey()), CoreMatchers.not(emptyOrNullString()));
                    break;
            }
        }
    }

    @When("^Customer provides all required data for Checkout page$")
    public void populateCheckoutPageDetails(Map<String, String> customerDetails) {
        CheckoutPage checkoutPage = getFromContext(PAGE);

        for (Map.Entry<String, String> expectedEntry : customerDetails.entrySet()) {
            checkoutPage.populateFieldText(expectedEntry.getKey(), expectedEntry.getValue());
        }
    }
}
