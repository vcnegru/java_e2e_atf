package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.config.WebDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.constants.Constants.CAT_GENOME_TEST;
import static com.atf.template.ui.cucumber.context.DataKeys.PAGE;
import static com.atf.template.ui.cucumber.context.DataKeys.PRODUCT;
import static com.atf.template.ui.cucumber.context.ScenarioContext.saveToContext;

@Slf4j
public class HomePage extends BasePage {
//    private WebDriver driver;

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
    @FindBy(xpath = "//div[@class='hero-section__title']")
    private WebElement homePageHeader;
    @FindBy(css = ".hero-section__desc")
    private WebElement homePageDescription;
    //  Top Header Menu items----------------------------------
    @FindBy(xpath = "//img[@alt='top logo']")
    private WebElement topLogoBasePaws;
    @FindBy(xpath = "//div[normalize-space()='For Dogs']")
    private WebElement topMenuForDogs;
    @FindBy(xpath = "//div[normalize-space()='For Cats']")
    private WebElement topMenuForCats;
    @FindBy(xpath = "//div[normalize-space()='Learn']")
    private WebElement topMenuLearn;
    @FindBy(xpath = "//div[normalize-space()='About']")
    private WebElement topMenuAbout;
    @FindBy(xpath = "//div[normalize-space()='Refer a Friend']")
    private WebElement topMenuReferFriend;
    @FindBy(xpath = "//a[normalize-space()='Register Kit']")
    private WebElement topMenuRegisterKit;
    @FindBy(xpath = "//a[normalize-space()='Sign Up']")
    private WebElement topMenuSignUp;

    //  Dog Products-------------------------------------------
    @FindBy(xpath = "//img[@alt='IMG Packaging for Dog Product']")
    private WebElement dogProductsImage;
    @FindBy(xpath = "//p[normalize-space()='Breed + Health Dog DNA Test']")
    private WebElement dogProductsName;
    //  Cat Products-------------------------------------------
    @FindBy(xpath = "//img[@alt='IMG New Packaging Breed Health for Cats']")
    private WebElement catBreedProductsImage;
    @FindBy(xpath = "//p[normalize-space()='Breed + Health Cat DNA Test']")
    private WebElement catBreedProductsName;
    @FindBy(xpath = "//img[@alt='IMG New Packaging Oral Test For Cats']")
    private WebElement catOralProductsImage;
    @FindBy(xpath = "//p[normalize-space()='Oral Health Test for Cats']")
    private WebElement catOralProductsName;
    @FindBy(xpath = "//img[@alt='IMG New Packaging Whole Genome Seq for Cats WGS']")
    private WebElement catGenomeProductsImage;
    @FindBy(xpath = "//p[normalize-space()='Whole Genome Test']")
    private WebElement catGenomeProductsName;

    public HomePage(WebDriver driver) {
        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
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
        return new HomePage(WebDriverConfig.getDriver());
    }

    public DogDNAProductPage dogProductPageIsOpen() {
        return new DogDNAProductPage(WebDriverConfig.getDriver());
    }

    public CatProductsPage catProductPageIsOpen() {
        return new CatProductsPage(WebDriverConfig.getDriver());
    }

    public void clickCloseOfferButton() {
        if (checkIfPresent(closeOffer)) {
            closeOffer.click();
        }
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


    public void checkIfPresentMenuHomePage(String menuElement) {
        switch (menuElement) {
            case "Logo":
                checkIfPresent(headerLogo);
                break;
            case "For Dogs":
                checkIfPresent(topMenuForDogs);
                break;
            case "For Cats":
                checkIfPresent(topMenuForCats);
                break;
            case "Learn":
                checkIfPresent(topMenuLearn);
                break;
            case "About":
                checkIfPresent(topMenuAbout);
                break;
            case "Refer a Friend":
                checkIfPresent(topMenuReferFriend);
                break;
            case "Register Kit":
                checkIfPresent(topMenuRegisterKit);
                break;
            case "Sign Up":
                checkIfPresent(topMenuSignUp);
                break;
            case "Mini Cart":
                checkIfPresent(topMenuMiniCart);
                break;
            default:
                throw new NoSuchElementException("Such element is not defined on page: " + menuElement);
        }
    }

    public void hoverMenuHomePage(String menuElement) {
        Actions actions = new Actions(WebDriverConfig.getDriver());
        switch (menuElement) {
            case "Logo":
                actions.moveToElement(headerLogo).build().perform();
                break;
            case "For Dogs":
                actions.moveToElement(topMenuForDogs).build().perform();
                break;
            case "For Cats":
                actions.moveToElement(topMenuForCats).build().perform();
                break;
            case "Learn":
                actions.moveToElement(topMenuLearn).build().perform();
                break;
            case "About":
                actions.moveToElement(topMenuAbout).build().perform();
                break;
            case "Register Kit":
                actions.moveToElement(topMenuRegisterKit).build().perform();
                break;
            case "Sign Up":
                actions.moveToElement(topMenuSignUp).build().perform();
                break;
            case "Refer a Friend":
                actions.moveToElement(topMenuReferFriend).build().perform();
                break;
            case "Mini Cart":
                actions.moveToElement(topMenuMiniCart).build().perform();
                break;
            default:
                throw new NoSuchElementException("Such element is not defined on page: " + menuElement);

        }
    }

    public void checkProducts(String product) {
//        Boolean isPresent = true;
        switch (product) {
            case "Breed + Health Dog DNA Test":
                checkIfPresent(dogProductsImage);
                checkIfPresent(dogProductsName);
                break;
            case "Breed + Health Cat DNA Test":
                checkIfPresent(catBreedProductsImage);
                checkIfPresent(catBreedProductsName);
                break;
            case "Oral Health Test for Cats":
                checkIfPresent(catOralProductsImage);
                checkIfPresent(catOralProductsName);
                break;
            case "Whole Genome Test":
                checkIfPresent(catGenomeProductsImage);
                checkIfPresent(catGenomeProductsName);
                break;
            default:
                throw new NoSuchElementException("Such element is not defined on page: " + product);
        }
//        if (!isPresent) {
//            throw new NoSuchElementException("Such element is not defined/visible on page: " + product);
//        }
    }

    public void navigateToProduct(String product) {
//        Boolean isPresent = true;
        Actions actions = new Actions(driver);

        log.warn("Start navigateToProduct: {}", product);
        switch (product) {
            case "Breed + Health Dog DNA Test":
                log.warn("Start click dogProductsImage " + dogProductsImage.getText());
                actions.moveToElement(topMenuForDogs).build().perform();
                dogProductsImage.click();
                log.warn("Clicked dogProductsImage");
                DogDNAProductPage page = new DogDNAProductPage(driver);
                saveToContext(PAGE, page);
                saveToContext(PRODUCT, product);
                break;
            case "Breed + Health Cat DNA Test":
//                click(catBreedProductsName);
                actions.moveToElement(topMenuForCats).build().perform();
                catBreedProductsImage.click();
                CatDNAProductPage catDNAPage = new CatDNAProductPage(driver);
                saveToContext(PAGE, catDNAPage);
                saveToContext(PRODUCT, product);
                break;
            case "Oral Health Test for Cats":
//                click(catOralProductsName);
                actions.moveToElement(topMenuForCats).build().perform();
                catOralProductsImage.click();
                CatOralProductPage catOralProductPage = new CatOralProductPage(driver);
                saveToContext(PAGE, catOralProductPage);
                saveToContext(PRODUCT, product);
                break;
            case "Whole Genome Test":
//                click(catGenomeProductsName);
                actions.moveToElement(topMenuForCats).build().perform();
                catGenomeProductsImage.click();
                CatGenomeProductPage catGenomeProductPage = new CatGenomeProductPage(driver);
                saveToContext(PAGE, catGenomeProductPage);
                saveToContext(PRODUCT, CAT_GENOME_TEST);
                break;
            default:
                throw new NoSuchElementException("Such element is not defined on page: " + product);
        }
        log.warn("End navigateToProduct");
    }


}
