package com.atf.template.ui.cucumber.stepDefinitions;

import com.atf.template.ui.cucumber.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;

//import static com.atf.template.ui.cucumber.config.WebDriverConfiguration.getDriver;
import static com.atf.template.ui.cucumber.config.WebDriverConfig.getDriver;

public class BaseSteps {
    protected WebDriver driver = getDriver();

    public BaseSteps() {
        this.driver = WebDriverConfig.getDriver();
    }
}
