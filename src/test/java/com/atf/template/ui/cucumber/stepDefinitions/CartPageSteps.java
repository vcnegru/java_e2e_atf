package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.config.PropertiesKeys;
import com.atf.template.ui.cucumber.context.MiniCart;
import com.atf.template.ui.cucumber.helper.Product;
import com.atf.template.ui.cucumber.helper.ProductMiniCart;
import com.atf.template.ui.cucumber.pageObjects.CartPage;
import com.atf.template.ui.cucumber.pageObjects.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.actions.GenericActions.decimalFormat;
import static com.atf.template.ui.cucumber.assertions.CustomAssertions.assertThat;
import static com.atf.template.ui.cucumber.config.PropertyReader.getProperty;
import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.MiniCart.getMiniCartSize;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.*;

public class CartPageSteps extends BasePageSteps {

    public final static String MY_CART_HEADER_TEXT = "My Cart";

    @And("^Cart Page is displayed$")
    public void cartPageIsDisplayed() {
        CartPage cartPage = (CartPage) getFromContext(PAGE);
        assertThat("Cart Page header is displayed: " + MY_CART_HEADER_TEXT, cartPage.getCartHeader(), is(MY_CART_HEADER_TEXT));
    }

    @And("^Cart Page contains following products:$")
    public void checkCartPageProducts(Map<String, String> expectedEntities) throws InterruptedException {
        CartPage cartPage = new CartPage(driver);

        ArrayList<Product> products = parseCartPageProductsList();

        for (Map.Entry<String, String> expectedEntry : expectedEntities.entrySet()) {
            int index = extractIndexVariable(expectedEntry.getKey());
            String property = extractPropertyVariable(expectedEntry.getKey());
            if ("name".equals(property)) {
                String actualName = products.get(index).getProductDescription();
                assertThat(format("product[%s].name is", index), actualName, is(expectedEntry.getValue()));
            } else if ("quantity".equals(property)) {
                String actualQuantity = products.get(index).getTestSetCount();
                assertThat(format("product[%s].quantity is", index), actualQuantity, is(expectedEntry.getValue()));
            } else if ("count".equals(property)) {
                int actualCount = products.get(index).getProductCount();
                assertThat(format("product[%s].count is", index), actualCount, is(Integer.parseInt(expectedEntry.getValue())));
            } else if ("subtotal".equals(property)) {
                String actualSubtotal = decimalFormat.format(products.get(index).getProductSubtotalPrice());
                assertThat(format("product[%s].subtotal is", index), actualSubtotal, is(expectedEntry.getValue()));
            }
        }
    }

    @And("^Cart Page contains following total details:$")
    public void checkCartPageTotalSection(Map<String, String> expectedEntities) {
        CartPage cartPage = (CartPage) getFromContext(PAGE);
        assertThat("total price is: ", expectedEntities.get("total"), is(cartPage.getSubtotal()));
        assertThat("shipping conditions are: ", expectedEntities.get("shipping"), is(cartPage.getShippingConditions()));
    }

    public ArrayList<Product> parseCartPageProductsList() throws InterruptedException {
        ArrayList<Product> products = new ArrayList<>();
        String cartRowXpath = "//div[@class='cart__row']";
        // Find all product elements
        sleep(2000);
        List<WebElement> productDivs = driver.findElements(By.xpath(cartRowXpath));

        // Parse product details
        int index = 1;
        for (WebElement div : productDivs) {
            sleep(2000);
            checkIfPresent(div.findElement(By.xpath(cartRowXpath + "[" + index + "]" + "//a[@class='cart-product__title']")));
            String productName = div.findElement(By.xpath(cartRowXpath + "[" + index + "]" + "//a[@class='cart-product__title']")).getText();
            String productQuantity = div.findElement(By.xpath(cartRowXpath + "[" + index + "]" + "//p[@class='cart-product__quantity']")).getText();
            String productCount = div.findElement(By.xpath(cartRowXpath + "[" + index + "]" + "//div//input[@class='counter__input input']")).getAttribute("value");

            String totalCost = div.findElement(By.xpath(cartRowXpath + "[" + index + "]" + "//div[@class='total-price__new-price']"))
                    .getText()
                    .replace("$", "")
                    .replace(",", "");

            Product product = new Product(productName, productQuantity, productCount, totalCost);
            products.add(product);
            index++;
        }
        return products;
    }

    @When("^user applies 'Discount code' on Cart Page$")
    public void applyDiscountCodeCartPage() {
        CartPage cartPage = (CartPage) getFromContext(PAGE);
        String discountCode = getProperty(PropertiesKeys.DISCOUNT_CODE);
        cartPage.populateDiscountCodeInput(discountCode);
        cartPage.clickDiscountCodeApplyButton();
    }

    @And("^label for applied discount code is displayed$")
    public void verifyDiscountCodeLabelIsDisplayed() {
        CartPage cartPage = (CartPage) getFromContext(PAGE);
        String discountCode = getProperty(PropertiesKeys.DISCOUNT_CODE);
        assertThat("applied discount code label is displayed: ", cartPage.getCouponText(), equalToIgnoringCase(discountCode));
    }

    @When("^Customer clicks on remove coupon on Cart Page$")
    public void removeDiscountCodeCartPage() {
        CartPage cartPage = (CartPage) getFromContext(PAGE);
        cartPage.removeCoupon();
    }
}
