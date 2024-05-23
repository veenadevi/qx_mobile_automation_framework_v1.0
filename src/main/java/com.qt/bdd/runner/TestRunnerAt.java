package com.qt.bdd.runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(features = "src//test//resources//features",
        tags = "@smoke",
        dryRun = false,
        glue = "com/qt/stepdefinitions",
        plugin = {"pretty",
                "html:test-output/cucumber-reports/html-report.html",
                "json:test-output/cucumber-reports/json-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true)

public class TestRunnerAt {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        String capabilitiesFilePath = System.getProperty("user.dir") + "\\caps\\capabilitiesAt.json";
        System.setProperty("prop.capabilities", capabilitiesFilePath);
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

    @Test(groups = "cucumber scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        return testNGCucumberRunner.provideScenarios();
    }
}
