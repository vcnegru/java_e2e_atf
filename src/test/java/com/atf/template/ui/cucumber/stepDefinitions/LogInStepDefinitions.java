package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.pageObjects.HomePage;
import com.atf.template.ui.cucumber.pageObjects.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static assertions.CustomAssertions.assertThat;
import static com.atf.template.ui.cucumber.config.PropertiesKeys.BASE_URL;
import static com.atf.template.ui.cucumber.config.PropertyReader.getProperty;
import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;
import static com.atf.template.ui.cucumber.context.ScenarioContext.saveToContext;
import static org.hamcrest.Matchers.is;

@Slf4j
public class LogInStepDefinitions extends BaseSteps {
    @Given("^user accesses BasePaws page$")
    public void userHomePage() {
        driver.get(getProperty(BASE_URL));
        log.info("Customer accesses BasePaws page");
        HomePage homePage = new HomePage(driver);
        saveToContext(PAGE, homePage);
    }

    @When("^(?:Popup|Header) '(.*)' is displayed$")
    public void validateOfferPopupIsDisplayed(String elementName) {
        HomePage homePage =getFromContext(PAGE);
        switch (elementName) {
            case "Exclusive Offer":
                homePage.popupOfferIsDisplayed();
                break;
            case "Cookies":
                homePage.popupCookiesIsDisplayed();
                break;
            case "Announcement Bar":
                homePage.isHeaderAnnouncementBarPresent();
                break;
            default:
                throw new NoSuchElementException("Popup has no locator defined: " + elementName);
        }
        saveToContext(PAGE, homePage);
    }

    @Then("^user clicks on '(.*)' button$")
    public void userClicksOnButton(String button) {
        HomePage homePage = getFromContext(PAGE);
        switch (button) {
            case "Close Offer Popup":
                homePage.clickCloseOfferButton();
                break;
            case "Accept Cookies":
                homePage.clickAcceptCookiesButton();
                break;
            default:
                throw new NoSuchElementException("Definition not found for button/element: " + button);
        }

    }

    @Then("^user clicks on '(.*)' header menu$")
    public void userHoversHeaderMenuItem(String headerMenu) {
        HomePage homePage = getFromContext(PAGE);
        try {
            homePage.checkIfPresentMenuHomePage(headerMenu);
            homePage.hoverMenuHomePage(headerMenu);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Definition not found for button/element: " + headerMenu);
        }

    }

    @Then("^'(.+)' page is open$")
    public void productsPageIsOpen(String pageName) {
        HomePage homePage = getFromContext(PAGE);
        switch (pageName) {
            case "Products" -> saveToContext(PAGE, homePage.productPageIsOpen());
        }
    }

    @When("^following error message is displayed:$")
    public void followingErrorMessageIsDisplayed(String errorMessage) {
        HomePage homePage = getFromContext(PAGE);
        assertThat("login error message is displayed", homePage.getLoginErrorMessage(), is(errorMessage));
    }

    @When("^user opens menu$")
    public void userOpensMenu() {
        ProductsPage productsPage = getFromContext(PAGE);
        productsPage.openMenu();
    }

    @Then("^'(.+)' menu label is displayed$")
    public void userIsLogged(String menuLabel) {
        ProductsPage productsPage = getFromContext(PAGE);
        assertThat(menuLabel + "menu label is displayed", productsPage.getMenuLabel(0), is(menuLabel));
    }

    @Then("{string} page is displayed with following details:")
    public void homePageIsDisplayedWithFollowingDetails(String pageName, Map<String, String> pageDetails) throws IOException {
        HomePage homePage = getFromContext(PAGE);
        for (Map.Entry<String, String> entry : pageDetails.entrySet()) {
//            log.warn("Key: " + entry.getKey().toString());
//            log.warn("Value: " + entry.getValue().toString());
            if ("header".equals(entry.getKey())) {
                homePage.isHomePageHeaderPresent(entry.getValue());
            } else if ("description".equals(entry.getKey())) {
                homePage.isHomePageDescriptionPresent(entry.getValue());
            } else {
                throw new NoSuchElementException("Such element is not defined on page: " + entry.getKey());
            }
        }
    }

//    @And("^{string} page header menu elements are displayed$")
//    public void pageHeaderMenuElements(String pageName, List<String> menuElements) {
//        HomePage homePage = getFromContext(PAGE);
//        for (String menuItem : menuElements) {
//            homePage.checkIfPresentMenuHomePage(menuItem);
//        }
//    }

    @And("{string} page header menu elements are displayed")
    public void homePageHeaderMenuElementsAreDisplayed(String pageName, List<String> menuElements) {
        HomePage homePage = getFromContext(PAGE);
        for (String menuItem : menuElements) {
            homePage.checkIfPresentMenuHomePage(menuItem);
        }
    }

    @Then("^(?:Dog|Cat) products are displayed:$")
    public void productsAreDisplayed(List<String> dogProducts) {
        HomePage homePage = getFromContext(PAGE);
        for (String dogProduct : dogProducts) {
            homePage.checkProducts(dogProduct);
        }
    }
//
//    @And("{string} page header menu elements are displayed")
//    public void homePageHeaderMenuElementsAreDisplayed() {
//    }
}

