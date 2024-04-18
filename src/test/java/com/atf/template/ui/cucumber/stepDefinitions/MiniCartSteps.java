package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.context.MiniCart;
import com.atf.template.ui.cucumber.helper.ProductMiniCart;
import com.atf.template.ui.cucumber.pageObjects.MiniCartProductPage;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.atf.template.ui.cucumber.actions.GenericActions.checkIfPresent;
import static com.atf.template.ui.cucumber.assertions.CustomAssertions.assertThat;
import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.DataKeys.PREV_PAGE;
import static com.atf.template.ui.cucumber.context.MiniCart.getMiniCartSize;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;
import static com.atf.template.ui.cucumber.context.ScenarioContext.saveToContext;
import static com.atf.template.ui.cucumber.pageObjects.MiniCartProductPage.extractIndexVariable;
import static com.atf.template.ui.cucumber.pageObjects.MiniCartProductPage.extractPropertyVariable;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.*;

@Slf4j
public class MiniCartSteps extends BaseSteps{
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
                double actualSubtotal = products.get(index).getProductSubtotalPrice();
                assertThat(format("product[%s].subtotal is", index), actualSubtotal, is(Double.parseDouble(expectedEntry.getValue())));
                assertThat(format("product[%s].subtotal is", index), actualSubtotal, is(MiniCart.getByIndex(index).getProductSubtotalPrice()));
            }
        }
        assertThat("total price is: ", miniCartProductPage.getTotalPrice(),is(MiniCart.getTotalAmount()));
        assertThat("total items count is(1): ", miniCartProductDivs.size(),is(getMiniCartSize()));
        assertThat("total items count is(2): " + "Subtotal (" + getMiniCartSize() + " items)", miniCartProductPage.getTotalQuantity() ,containsString("Subtotal (" + getMiniCartSize() + " item"));
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
            String totalCost = div.findElement(By.xpath(miniCartRowXpath + "[" + index + "]" + "//div[@class='total-price__new-price']")).getText().replace("$", "");

            ProductMiniCart product = new ProductMiniCart(productName, productQuantity, Integer.valueOf(productCount), Double.valueOf(totalCost));
            products.add(product);
            index++;
        }

        // Close WebDriver
//        driver.quit();

        return products;
    }
}
