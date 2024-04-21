package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.context.MiniCart;
import com.atf.template.ui.cucumber.helper.ProductMiniCart;
import com.atf.template.ui.cucumber.pageObjects.CartPage;
import com.atf.template.ui.cucumber.pageObjects.CheckoutPage;
import com.atf.template.ui.cucumber.pageObjects.MiniCartProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.atf.template.ui.cucumber.actions.GenericActions.checkIfPresent;
import static com.atf.template.ui.cucumber.actions.GenericActions.decimalFormat;
import static com.atf.template.ui.cucumber.actions.GenericActions.extractIndexVariable;
import static com.atf.template.ui.cucumber.actions.GenericActions.extractPropertyVariable;
import static com.atf.template.ui.cucumber.assertions.CustomAssertions.assertThat;
import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.DataKeys.PREV_PAGE;
import static com.atf.template.ui.cucumber.context.MiniCart.getMiniCartSize;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;
import static com.atf.template.ui.cucumber.context.ScenarioContext.saveToContext;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.*;

@Slf4j
public class MiniCartPageSteps extends BasePageSteps {
    @And("^user clicks on Close mini cart button$")
    public void closeMiniCart() {
        MiniCartProductPage miniCartProductPage = getFromContext(PAGE);
        miniCartProductPage.clickCloseMiniCart();
        log.info("Close mini cart page.");
        saveToContext(PAGE, getFromContext(PREV_PAGE));
        saveToContext(PREV_PAGE, miniCartProductPage);
    }

    @And("^mini cart has following items:$")
    public void miniCartHasItems(Map<String, String> items) throws InterruptedException {
        MiniCartProductPage miniCartProductPage = getFromContext(PAGE);
        miniCartProductPage.checkMiniCartHeaderIsDisplayed();

        List<WebElement> miniCartProductDivs = driver.findElements(By.xpath("//div[@class='cart-product__product']"));
        List<ProductMiniCart> products = parseCartPage();

        for (Map.Entry<String, String> expectedEntry : items.entrySet()) {
            int index = extractIndexVariable(expectedEntry.getKey());
            String property = extractPropertyVariable(expectedEntry.getKey());
            if ("name".equals(property)) {
                String actualName = products.get(index).getProductName();
                assertThat(format("product[%s].name is", index), actualName, is(expectedEntry.getValue()));
                assertThat(format("product[%s].name is", index), actualName, is(MiniCart.getByIndex(index).getProductName()));
            } else if ("quantity".equals(property)) {
                String actualQuantity = products.get(index).getProductQuantity();
                assertThat(format("product[%s].quantity is", index), actualQuantity, is(expectedEntry.getValue()));
                assertThat(format("product[%s].quantity is", index), actualQuantity, is(MiniCart.getByIndex(index).getProductQuantity()));
            } else if ("count".equals(property)) {
                int actualCount = products.get(index).getProductCount();
                assertThat(format("product[%s].count is", index), actualCount, is(Integer.parseInt(expectedEntry.getValue())));
                assertThat(format("product[%s].count is", index), actualCount, is(MiniCart.getByIndex(index).getProductCount()));
            } else if ("subtotal".equals(property)) {
                String actualSubtotal = decimalFormat.format(products.get(index).getProductSubtotalPrice());
//;                String actualSubtotal = String.format("%,.2f", products.get(index).getProductSubtotalPrice());
                assertThat(format("product[%s].subtotal is", index), actualSubtotal, is(expectedEntry.getValue()));
                assertThat(format("product[%s].subtotal is", index), actualSubtotal, is(decimalFormat.format(MiniCart.getByIndex(index).getProductSubtotalPrice())));
            }
        }

    }

    @And("^mini cart total quantities are as follow:$")
    public void miniCartTotals(Map<String, String> items) {
        MiniCartProductPage miniCartProductPage = getFromContext(PAGE);
        assertThat("total price is: ", items.get("cart_total_price"), is(decimalFormat.format(MiniCart.getTotalAmount())));
        assertThat("total products list size is: ", Integer.parseInt(items.get("cart_total_list_size")), is(getMiniCartSize()));
        assertThat("total items count is: " + "Subtotal (" + Integer.parseInt(items.get("cart_products_count")) + " items)", miniCartProductPage.getTotalQuantity(), containsString("Subtotal (" + items.get("cart_products_count") + " item"));

    }

    public List<ProductMiniCart> parseCartPage() throws InterruptedException {
        List<ProductMiniCart> products = new ArrayList<>();
        String miniCartRowXpath = "//div[@class='mini-cart__product cart-product']";
        // Find all product elements
        sleep(2000);
        List<WebElement> productDivs = driver.findElements(By.xpath("//div[@class='mini-cart__product cart-product']"));

        // Parse product details
        int index = 1;
        for (WebElement div : productDivs) {
            sleep(2000);
            checkIfPresent(div.findElement(By.xpath(miniCartRowXpath + "[" + index + "]" + "//div[@class='cart-product__title']")));
            String productName = div.findElement(By.xpath(miniCartRowXpath + "[" + index + "]" + "//div[@class='cart-product__title']")).getText();
            String productQuantity = div.findElement(By.xpath(miniCartRowXpath + "[" + index + "]" + "//div[@class='cart-product__quantity']")).getText();
            String productCount = div.findElement(By.xpath(miniCartRowXpath + "[" + index + "]" + "//div//input[@class='counter__input input']")).getAttribute("value");

//            double productPrice = Double.parseDouble(div.findElement(By.className("product_price")).getText());
            String totalCost = div.findElement(By.xpath(miniCartRowXpath + "[" + index + "]" + "//div[@class='total-price__new-price']"))
                    .getText()
                    .replace("$", "")
                    .replace(",", "");

            ProductMiniCart product = new ProductMiniCart(productName, productQuantity, Integer.valueOf(productCount), Double.valueOf(totalCost));
            products.add(product);
            index++;
        }

        // Close WebDriver
//        driver.quit();

        return products;
    }

    @And("^user clicks checkout button$")
    public void clickCheckoutButton() {
        MiniCartProductPage miniCartProductPage = (MiniCartProductPage) getFromContext(PAGE);
        miniCartProductPage.clickMiniCartCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        saveToContext(PAGE, checkoutPage);
    }

    @When("^customer clicks on View Cart button$")
    public void clickViewCartButton() {
        MiniCartProductPage miniCartProductPage = (MiniCartProductPage) getFromContext(PAGE);
        miniCartProductPage.clickViewCartButton();
        CartPage cartPage = new CartPage(driver);
        saveToContext(PAGE, cartPage);
    }
}
