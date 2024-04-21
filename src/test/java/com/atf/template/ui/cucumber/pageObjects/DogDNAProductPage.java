package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.config.WebDriverConfig;
import com.atf.template.ui.cucumber.helper.ProductMiniCart;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

import static com.atf.template.ui.cucumber.actions.GenericActions.*;
import static com.atf.template.ui.cucumber.context.DataKeys.TESTS_SET;
import static com.atf.template.ui.cucumber.context.MiniCart.addProductToMiniCart;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;

@Slf4j
public class DogDNAProductPage extends ProductPage {
//    private WebDriver driver;

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
    private WebElement dogBreedProductSuptitle;
    @FindBy(xpath = "//h1[contains(text(),'Breed + Health Dog DNA Test')]")
    private WebElement dogBreedProductDetailTitle;

//    @FindBy(css = ".product-detail__btn.btn.btn--primary")
    @FindBy(xpath = "//div/a[@class='product-detail__btn btn btn--primary ']")
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

    public DogDNAProductPage(WebDriver driver) {
        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    public String getProductName(String product) {
        checkIfPresent(dogBreedProductDetailTitle);
        return dogBreedProductDetailTitle.getText();
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

    public void addProductToCart(String productName, int testCount) {
        double productPrice = Double.parseDouble(newPriceLabel.getText().replace("$",""));
        int productCount = getProductCount();
        moveToWithJS(addToCartButton);
        click(addToCartButton);

        ProductMiniCart productMiniCart =
                new ProductMiniCart(productName, getFromContext(TESTS_SET), getProductCount(), productPrice * productCount);
        addProductToMiniCart(productMiniCart);
        log.info("Product Added to in MEM MiniCart: {}", productMiniCart.toString());
    }


    public static Boolean checkIfPresent(WebElement element) {
        try {
            waitFluent().until(ExpectedConditions.visibilityOf(element));
            log.info("Element name '{}' is present on the page (element='{}')", element.getText(), element.toString());
        } catch (TimeoutException exception) {
            log.error("Element is NOT present on the page: {}", element.toString());
            return false;
        }
        return true;
    }
}
