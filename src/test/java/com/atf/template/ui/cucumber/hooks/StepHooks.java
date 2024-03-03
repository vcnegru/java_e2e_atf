package com.atf.template.ui.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.atf.template.ui.cucumber.config.WebDriverConfiguration.getDriver;
import static com.atf.template.ui.cucumber.context.ScenarioContext.resetContext;

@Slf4j
public class StepHooks {
    @AfterAll
    public static void afterAll() {
        getDriver().close();
        getDriver().quit();
    }

    @Before
    public void before(Scenario scenario) {
        log.info("[ TEST STARTED ] {}", scenario.getName());
    }
    @After
    public  void after() {
        resetContext();
    }
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
