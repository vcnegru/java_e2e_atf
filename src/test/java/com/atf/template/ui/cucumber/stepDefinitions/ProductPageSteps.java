package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.pageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.atf.template.ui.cucumber.assertions.CustomAssertions.assertThat;
import static com.atf.template.ui.cucumber.constants.Constants.*;
import static com.atf.template.ui.cucumber.context.DataKeys.*;
import static com.atf.template.ui.cucumber.context.MiniCart.getMiniCartItemsCount;
import static com.atf.template.ui.cucumber.context.MiniCart.getMiniCartSize;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;
import static com.atf.template.ui.cucumber.context.ScenarioContext.saveToContext;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.is;

@Slf4j
public class ProductPageSteps extends BaseSteps {


    @And("^user clicks on product:$")
    public void userClicksOnProduct(String product) {
        HomePage homePage = getFromContext(PAGE);
        homePage.checkProducts(product);
        homePage.navigateToProduct(product);
//        saveToContext(PREV_PAGE, homePage);
//        saveToContext(PAGE, new ProductPage(driver));
        saveToContext(PRODUCT, product);
    }

    @Then("^'(.*)' dog product page is displayed with details:$")
    public void breedHealthDogDNATestDogProductPageIsDisplayed(String product, List<String> dogProductPageDetails) {
        DogDNAProductPage page = (DogDNAProductPage) getFromContext(PAGE);
        for (String dogProduct : dogProductPageDetails) {
            page.checkProductDetails(dogProduct);
        }
    }

    @Then("^'(.*)' cat product page is displayed with details:$")
    public void catProductPageIsDisplayed(String product, List<String> productPageDetails) {
//        CatProductsPage catProductsPage = getFromContext(PAGE);
//        for (String catProduct : productPageDetails) {
//            catProductsPage.checkProductDetails(catProduct);
//        }
        switch (product) {
            case CAT_GENOME_TEST:
                CatGenomeProductPage pageCatGenome = getFromContext(PAGE);
                for (String productDetail : productPageDetails) {
                    pageCatGenome.checkProductDetails(productDetail);
                }
                break;
            case CAT_ORAL_TEST:
                CatOralProductPage pageCatOral = getFromContext(PAGE);
                for (String productDetail : productPageDetails) {
                    pageCatOral.checkProductDetails(productDetail);
                }
                break;
            case CAT_DNA_TEST:
                CatDNAProductPage pageCatDNA = getFromContext(PAGE);
                for (String productDetail : productPageDetails) {
                    pageCatDNA.checkProductDetails(productDetail);
                }
                break;
            case DOG_DNA_TEST:
                DogDNAProductPage pageDogDNA = getFromContext(PAGE);
                for (String productDetail : productPageDetails) {
                    pageDogDNA.checkProductDetails(productDetail);
                }
        }
    }


    @When("^user selects '(.*)' set of product$")
    public void userSelectsTestSetsOption(String testsSet) {
        ProductPage productPage = getFromContext(PAGE);
        String product = getFromContext(PRODUCT);
        switch (product) {
            case CAT_GENOME_TEST:
            case CAT_ORAL_TEST:
                saveToContext(TESTS_SET, "1 test");
                break;
            case CAT_DNA_TEST:
                CatDNAProductPage pageCatDNA = getFromContext(PAGE);
                pageCatDNA.selectTestsCount(testsSet);
                saveToContext(TESTS_SET, testsSet);
                break;
            case DOG_DNA_TEST:
                DogDNAProductPage pageDogDNA = getFromContext(PAGE);
                pageDogDNA.selectTestsCount(testsSet);
                saveToContext(TESTS_SET, testsSet);
                break;
        }
    }

    @When("^user change quantity to '(.*)' product$")
    public void userChangeProductQuantity(int prodQuantity) {
        ProductPage productPage = getFromContext(PAGE);
//        driver.navigate().refresh();
        if (productPage.getProductCount() < prodQuantity) {
            for (int i = productPage.getProductCount(); i < prodQuantity; i++) {
                productPage.clickAddProduct();
            }
        } else if (productPage.getProductCount() > prodQuantity) {
            for (int i = productPage.getProductCount(); i > prodQuantity; i--) {
                productPage.clickRemoveProduct();
            }
        }
    }

    //  @When("^user adds (.*) item of '(.*) test\\(s\\)' '(.*)' product to cart$")
    @When("^user adds '(.*)' product to cart$")
    public void userAddProductToCart(String product) {
        // Once click on add product, product should be added to miniCart and to productsCart
        //so that expected content could be validated with the actual one
        int productsCount = 0;
        if (product.contains("Cat")) {
            CatProductsPage catProductsPage = new CatProductsPage(driver);
            productsCount = catProductsPage.getProductCount();
            switch (product) {
                case CAT_DNA_TEST:
                    CatDNAProductPage catDNAPage = getFromContext(PAGE);
                    catDNAPage.addProductSetToCart(product, catDNAPage.getProductCount());
                    saveToContext(PREV_PAGE, catDNAPage);
                    break;
                case CAT_ORAL_TEST:
                    CatOralProductPage catOralPage = getFromContext(PAGE);
                    catOralPage.addProductSetToCart(product, catOralPage.getProductCount());
                    saveToContext(PREV_PAGE, catProductsPage);
                    break;
//                case CAT_ORAL_TEST:
//                    catProductsPage.addProductToCart(product, testsCount);
//                    log.info("IMPLEMENT logic for add {} product", CAT_ORAL_TEST);
//                    break;
                case CAT_GENOME_TEST:
                    log.info("IMPLEMENT logic for add {} product", CAT_GENOME_TEST);
                    break;
                default:

            }
        } else if (product.contains("Dog")) {
            DogDNAProductPage dogDNAProductPage = new DogDNAProductPage(driver);
            productsCount = dogDNAProductPage.getProductCount();

//            dogProductsPage.selectTestsCount(testsCount);

//            saveToContext(PAGE, dogProductsPage);
            dogDNAProductPage.addProductToCart(product, dogDNAProductPage.getProductCount());
            saveToContext(PREV_PAGE, dogDNAProductPage);
        } else {
            log.info("Do nothing.");
        }
        MiniCartProductPage miniCartProductPage = new MiniCartProductPage(driver);
        saveToContext(PAGE, miniCartProductPage);
    }

    @And("^header cart icon has '(.*)' items$")
    public void headerCartIconHasItems(String itemsCount) {
        ProductPage productsPage = getFromContext(PAGE);
        assertThat(format("cart items count is %s", itemsCount), productsPage.getTextBase("cartCount"), is(itemsCount));
        assertThat(format("expected items count in cart is %s", getMiniCartSize()), Integer.parseInt(productsPage.getTextBase("cartCount")), is(getMiniCartItemsCount()));
    }

}
