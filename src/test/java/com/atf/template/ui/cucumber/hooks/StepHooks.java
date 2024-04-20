package com.atf.template.ui.cucumber.hooks;

//import com.atf.template.ui.cucumber.config.WebDriverConfiguration;
import com.atf.template.ui.cucumber.config.WebDriverConfig;
import io.cucumber.java.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//import static com.atf.template.ui.cucumber.config.WebDriverConfiguration.getDriver;
import static com.atf.template.ui.cucumber.config.WebDriverConfig.getDriver;
import static com.atf.template.ui.cucumber.context.MiniCart.resetMiniCartContext;
import static com.atf.template.ui.cucumber.context.ScenarioContext.resetContext;
import static java.lang.Thread.sleep;

@Slf4j
public class StepHooks {
    @AfterAll
    public static void afterAll() {
//        getDriver().close();
//        getDriver().quit();
    }
    @Before
    public void before(Scenario scenario) {
//        WebDriverConfiguration.getDriver();
        log.info("[ TEST STARTED ] {}", scenario.getName());
    }
    @After
    public  void after() {
//        resetContext();
//        resetMiniCartContext();
//        getDriver().close();
//        getDriver().quit();

//        resetCartContext();
    }

//    For Debug Only, causes heavyweight HTML Reports, adds Screenshot at Every Step
//    @AfterStep
//    public void addScreenshot(Scenario scenario) throws InterruptedException {
//        sleep(500);
//        final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
//        scenario.attach(screenshot, "image/png", scenario.getName());
//    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
//        getDriver().close();
//        getDriver().quit();
//        log.info("TearDown close & quit WebDriver!!!");
    }

}
