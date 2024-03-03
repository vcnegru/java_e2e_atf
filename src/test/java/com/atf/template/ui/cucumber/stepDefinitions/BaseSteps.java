package com.atf.template.ui.cucumber.stepDefinitions;

import org.openqa.selenium.WebDriver;

import static com.atf.template.ui.cucumber.config.WebDriverConfiguration.getDriver;

public class BaseSteps {
    protected WebDriver driver = getDriver();
}
