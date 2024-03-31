package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.pageObjects.CatProductsPage;
import com.atf.template.ui.cucumber.pageObjects.DogProductsPage;
import com.atf.template.ui.cucumber.pageObjects.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;

@Slf4j
public class ProductPageSteps extends BaseSteps{
    @And("^user clicks on product:$")
    public void userClicksOnProduct(String product) {
        HomePage homePage = getFromContext(PAGE);
        homePage.checkProducts(product);
        homePage.navigateToProduct(product);
    }

    @Then("^'(.*)' dog product page is displayed with details:$")
    public void breedHealthDogDNATestDogProductPageIsDisplayed(String product, List<String> dogProductPageDetails) {
        DogProductsPage dogProductsPage = getFromContext(PAGE);
        for (String dogProduct : dogProductPageDetails) {
            dogProductsPage.checkProductDetails(dogProduct);
        }
    }
    @Then("^'(.*)' cat product page is displayed with details:$")
    public void catProductPageIsDisplayed(String product, List<String> catProductPageDetails) {
        CatProductsPage catProductsPage = getFromContext(PAGE);
        for (String catProduct : catProductPageDetails) {
            catProductsPage.checkProductDetails(catProduct);
        }
    }
}
