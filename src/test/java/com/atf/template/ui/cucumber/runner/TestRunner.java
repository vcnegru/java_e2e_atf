package com.atf.template.ui.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        stepNotifications = true,
        features = "classpath:features",
        glue = "com.atf.template.ui.cucumber",
        tags = ("@Positive and not @Ignore")
//        tags = ("@Positive and not @Ignore and @run")
)
public class TestRunner {

}
