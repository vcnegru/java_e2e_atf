package com.atf.template.ui.cucumber.pageObjects;

import com.atf.template.ui.cucumber.helper.ProductMiniCart;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static com.atf.template.ui.cucumber.actions.GenericActions.checkIfPresent;
import static com.atf.template.ui.cucumber.actions.GenericActions.click;
import static com.atf.template.ui.cucumber.context.DataKeys.TESTS_SET;
import static com.atf.template.ui.cucumber.context.MiniCart.addProductToMiniCart;
import static com.atf.template.ui.cucumber.context.ScenarioContext.getFromContext;

@Slf4j
public class CatOralProductPage extends ProductPage{

    //    Breed + Health Dog DNA Test
    @FindBy(css = ".product-detail-carousel__img[fetchpriority='high']")
                     //*[@id="__next"]//section[1]//a
    private WebElement catBreedProductImage;
    @FindBy(css = ".product-detail-carousel__img[fetchpriority='high']")
    private WebElement catOralProductImage;
    @FindBy(css = ".product-detail-carousel__img[fetchpriority='high']")
    private WebElement catGenomeProductImage;
    // TODO define carousel elements to be validated
    //a[@class='product-detail-carousel__img-wrap']//img[@alt='BP_3d_boxes_refresh_Dog_DNA']
    //div[contains(@class,'product-detail-bottom-carousel__img-wrap')]//img[@alt='BP 3d boxes with-dog 02']
    //div[contains(@class,'product-detail-bottom-carousel__img-wrap')]//img[@alt='BP IMG Product-page Dog-HIW 1x1']
    @FindBy(xpath = "//div[@class='product-detail__suptitle']")
    private WebElement  catProductSuptitle;
    @FindBy(xpath = "//h1[contains(text(),'Breed + Health Cat DNA Test')]")
    private WebElement  catBreedProductDetailTitle;
    @FindBy(xpath = "//h1[contains(text(),'Oral Health Test for Cats')]")
    private WebElement  catOralProductDetailTitle;
    @FindBy(xpath = "//h1[contains(text(),'Whole Genome Sequencing')]")
    private WebElement  catGenomeProductDetailTitle;
    @FindBy(xpath = "//s[@class='product-detail__old-price']")
    private WebElement oldPriceLabel;
    @FindBy(xpath = "//strong[@class='product-detail__new-price']")
    private WebElement newPriceLabel;
    @FindBy(xpath = "//span[@class='product-detail__offertag offertag']")
    private WebElement specialOfferLabel;

    //TODO define radio button elements to validate selected state
//    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[1]")
//    private WebElement oneTestRadioButton;
//    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[2]")
//    private WebElement twoTestsRadioButton;
//    @FindBy(xpath = "//div[@class='product-detail__radio-button-wrap']//div[3]")
//    private WebElement threeTestsRadioButton;
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

    public CatOralProductPage(WebDriver driver) {
        super(driver);
    }

    public String getMenuLabel(int labelIndex) {
        checkIfPresent(menuLabels.get(labelIndex));
        return  menuLabels.get(labelIndex).getText();
    }

    public void openMenu() {
        click(menuButton);
    }


    public String getProductName(String product) {
        checkIfPresent(catBreedProductDetailTitle);
        return catBreedProductDetailTitle.getText();
    }

    public void checkProductDetails(String catProductPageDetails) {
        switch (catProductPageDetails) {
            case "Cat Breed Product Image":
                checkIfPresent(catBreedProductImage);
                break;
            case "Cat Oral Product Image":
                checkIfPresent(catOralProductImage);
                break;
            case "Cat Genome Product Image":
                checkIfPresent(catGenomeProductImage);
                break;
            case "Cat Product Suptile":
                checkIfPresent(catProductSuptitle);
                break;
            case "Cat Breed Product Detail Title":
                checkIfPresent(catBreedProductDetailTitle);
                break;
            case "Cat Oral Product Detail Title":
                checkIfPresent(catOralProductDetailTitle);
                break;
            case "Cat Genome Product Detail Title":
                checkIfPresent(catGenomeProductDetailTitle);
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
                throw new NoSuchElementException("Such element is not defined on page: " + catProductPageDetails);
        }
    }

    public void addProductSetToCart(String productName, int testCount) {
        double productPrice = Double.parseDouble(newPriceLabel.getText().replace("$",""));
        int productCount = getProductCount();
        click(addToCartButton);

        ProductMiniCart productMiniCart =
                new ProductMiniCart(productName, getFromContext(TESTS_SET), getProductCount(), productPrice * productCount);
        addProductToMiniCart(productMiniCart);
        log.info("Product Added to in MEM MiniCart: {}", productMiniCart.toString());
    }
}
