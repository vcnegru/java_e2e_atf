package com.atf.template.ui.cucumber.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.atf.template.ui.cucumber.actions.GenericActions.checkIfPresent;
import static com.atf.template.ui.cucumber.actions.GenericActions.click;

public class ProductsPage {
    private final WebDriver driver;
    @FindBy(css = "#react-burger-menu-btn")
    private WebElement menuButton;
    @FindBy(css = "nav > a")
    private List<WebElement> menuLabels;

    public ProductsPage(WebDriver driver) {
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
}
