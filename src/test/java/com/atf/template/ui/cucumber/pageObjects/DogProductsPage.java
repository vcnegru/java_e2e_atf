package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.stepDefinitions.BaseSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static com.atf.template.ui.cucumber.actions.GenericActions.checkIfPresent;
import static com.atf.template.ui.cucumber.actions.GenericActions.click;

public class DogProductsPage extends ProductPage {
    private final WebDriver driver;

//    Breed + Health Dog DNA Test
    @FindBy(xpath = "//a[@class='product-detail-carousel__img-wrap']//img[@alt='BP_3d_boxes_refresh_Dog_DNA']")
                     //a[@class='product-detail-carousel__img-wrap']//img[@alt='BP_3d_boxes_refresh_Cat_DNA']'
    //a[@class='product-detail-carousel__img-wrap']//img[@alt='Background']
    //*[@id="__next"]//a
    private WebElement dogBreedProductImage;
    // TODO define carousel elements to be validated
    //a[@class='product-detail-carousel__img-wrap']//img[@alt='BP_3d_boxes_refresh_Dog_DNA']
    //div[contains(@class,'product-detail-bottom-carousel__img-wrap')]//img[@alt='BP 3d boxes with-dog 02']
    //div[contains(@class,'product-detail-bottom-carousel__img-wrap')]//img[@alt='BP IMG Product-page Dog-HIW 1x1']
    @FindBy(xpath = "//div[@class='product-detail__suptitle']")
    private WebElement  dogBreedProductSuptitle;
    @FindBy(xpath = "//h1[contains(text(),'Breed + Health Dog DNA Test')]")
    private WebElement  dogBreedProductDetailTitle;
    @FindBy(xpath = "//s[@class='product-detail__old-price']")
    private WebElement oldPriceLabel;
    @FindBy(xpath = "//strong[@class='product-detail__new-price']")
    private WebElement newPriceLabel;
    @FindBy(xpath = "//span[@class='product-detail__offertag offertag']")
    private WebElement specialOfferLabel;

    //TODO define radio button elements to validate selected state
    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[1]")
    private WebElement oneTestRadioButton;
    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[2]")
    private WebElement twoTestsRadioButton;
    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[3]")
    private WebElement threeTestsRadioButton;
    @FindBy(xpath = "//button[@aria-label='Reduce the quantity of goods']")
    private WebElement productCounterRemoveButton;
    @FindBy(xpath = "//button[@aria-label='Increase the quantity of goods']")
    private WebElement productCounterAddButton;
    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement productCounter;
    @FindBy(css = ".product-detail__btn.btn.btn--primary")
    // .product-detail__btn.btn.btn--primary
    // //a[normalize-space()='Add to cart - $109.00|218.00|327.00']
    private WebElement addToCartButton;
//    @FindBy(xpath = "")
//    @FindBy(xpath = "")
//    @FindBy(xpath = "")
//    @FindBy(xpath = "")




    @FindBy(css = "#react-burger-menu-btn")
    private WebElement menuButton;
    @FindBy(css = "nav > a")
    private List<WebElement> menuLabels;

    public DogProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getMenuLabel(int labelIndex) {
        checkIfPresent(menuLabels.get(labelIndex));
        return  menuLabels.get(labelIndex).getText();
    }

    public void openMenu() {
        click(menuButton);
    }

    public void checkProductDetails(String dogProductPageDetails) {
        switch (dogProductPageDetails) {
            case "Dog Breed Product Image":
                checkIfPresent(dogBreedProductImage);
                break;
            case "Dog Breed Product Suptile":
                checkIfPresent(dogBreedProductSuptitle);
                break;
            case "Dog Breed Product Detail Title":
                checkIfPresent(dogBreedProductDetailTitle);
                break;
            case "Old price":
                checkIfPresent(oldPriceLabel);
                break;
            case "New price":
                checkIfPresent(newPriceLabel);
                break;
            case "Special Offer Button":
                checkIfPresent(specialOfferLabel);
                break;
            case "Product Count details":
                checkIfPresent(productCounter);
                break;
            case "Add to cart":
                checkIfPresent(addToCartButton);
                break;
            default:
                throw new NoSuchElementException("Such element is not defined on page: " + dogProductPageDetails);
        }
    }
}
