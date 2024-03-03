package com.atf.template.ui.cucumber.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;

public class HomePage {
    private final WebDriver driver;
    @FindBy(css = ".header__announce.announcement-bar")
    private WebElement headerAnnouncementBar;
    @FindBy(css = ".logo__wrap")
    private WebElement headerLogo;
    @FindBy(xpath = "//div[@data-testid='POPUP']")
    private WebElement popupOffer;
    @FindBy(xpath = "//span[contains(text(),'Want an ')]/span[contains(text(),'exclusive discount')]")
    private WebElement popupOfferHeader;
    @FindBy(css = ".ql-font-poppins")
    private WebElement popupOfferText;
    @FindBy(xpath = "//button[@aria-label='Close dialog 1']")
    private WebElement closeOffer;
    @FindBy(css = "div[class='CookieConsent']")
    private WebElement cookiesPopup;
    @FindBy(css = "#rcc-confirm-button")
    private WebElement acceptCookies;
    @FindBy(css = ".hero-section__title")
    private WebElement homePageHeader;
    @FindBy(css = ".hero-section__desc")
    private WebElement homePageDescription;
    //    -----------------------------------------------------
    @FindBy(css = "#login-button")
    private WebElement loginButton;
    @FindBy(css = "h3")
    private WebElement loginMessageError;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void popupOfferIsDisplayed() {
        checkIfPresent(popupOffer);
        checkIfPresent(popupOfferHeader);
        checkIfPresent(popupOfferText);
    }

    public void popupCookiesIsDisplayed() {
        checkIfPresent(cookiesPopup);
    }
    public HomePage homePageIsOpen() {
        return new HomePage(driver);
    }
    public ProductsPage productPageIsOpen() {
        return new ProductsPage(driver);
    }
    public void clickCloseOfferButton() {
        checkIfPresent(closeOffer);
        closeOffer.click();
    }
    public void clickAcceptCookiesButton() {
        checkIfPresent(acceptCookies);
        acceptCookies.click();
    }
    public void isHomePageHeaderPresent(String text) {
        checkIfPresent(homePageHeader);
        text.equals(homePageHeader.getText());
    }
    public void isHeaderAnnouncementBarPresent() {
        checkIfPresent(headerAnnouncementBar);
    }
    public void isHomePageDescriptionPresent(String text) {
        checkIfPresent(homePageDescription);
        text.contains(homePageDescription.getText());
    }
    public String getLoginErrorMessage() {
        return loginMessageError.getText();
    }
}
