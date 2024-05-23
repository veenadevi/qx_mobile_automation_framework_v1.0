package com.qt.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * This Class has all the Objects related to LiveCheck.
 *
 * @author
 */
public class LiveCheck {
    String projectDirectory = System.getProperty("user.dir");

// The rest of your code for taking a screenshot

    @FindAll({@FindBy(xpath = "//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"), @FindBy(xpath = "//android.widget.TextView[@text='English']"),})
    public MobileElement lnk_Language;
//    @FindAll({@FindBy(xpath = "//android.widget.EditText[@resource-id=\"test:id/Login-text_field\"]")})
//    public MobileElement editBoxMobileNumber;
@FindAll({@FindBy(xpath = "//android.widget.EditText[@resource-id=\"test:id/Login-text_field\"]"),
        @FindBy(xpath = "//XCUIElementTypeTextField[@name=\"test:id/Login-text_field\"]"),
})
public MobileElement editBoxMobileNumber;
    @FindAll({@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test:id/Login-Button\"]")})
    public MobileElement loginButton;
    @FindAll({@FindBy(xpath = "//android.widget.EditText[@resource-id=\"test:id/otp-code-field\"]"),

            @FindBy(xpath = "//android.view.ViewGroup/android.widget.EditText"), @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test:id/otp-code-field\"]")})
    public MobileElement editBoxOTP;
    @FindAll({@FindBy(xpath = "//android.widget.TextView[@resource-id=\"test:id/otp_confirm_text\"]"),})
    public MobileElement clickOnConfirm;
    @FindAll({@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test:id/house-keeping-button\"]/android.view.ViewGroup/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView[2]"),})
    public MobileElement clickOnLetsHaveLook;
    @FindAll({@FindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"),})
    public MobileElement clickOnAllow;
    @FindAll({@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test:id/switch\"]/android.view.ViewGroup/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.GroupView/com.horcrux.svg.GroupView/com.horcrux.svg.RectView"),})
    public MobileElement clickOnAgreeTerms;
    @FindAll({@FindBy(xpath = "//android.widget.TextView[@resource-id=\"test:id/continue_text\"]"),})
    public MobileElement continueButton;
    @FindAll({@FindBy(xpath = "//android.widget.TextView[@text='Login']"), @FindBy(xpath = "//android.widget.TextView[@content-desc=\"test:id/login-logo-text\"]"),})
    public MobileElement loginPageHeader;
    @FindAll({@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test:id/house-keeping\"]/android.widget.TextView[1]"),})
    public MobileElement pageAfterLoginSecurly;
    @FindAll({@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test:id/guide-screen\"]"),})
    public MobileElement guideScreen;
    ExtentReports extent;
    ExtentTest test;
    private AppiumDriver<MobileElement> driver;

    public static JSONArray readTestData(String filePath) throws Exception {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        return (JSONArray) parser.parse(reader);
    }

    public void enterMobileNumber(String mobileNumber) throws Exception {
        editBoxMobileNumber.sendKeys(mobileNumber);
    }

    public void clickOnSignInButton() {
        loginButton.click();
    }

    public void enterOTP(String OTP) throws Exception {
        Thread.sleep(3000);
        editBoxOTP.click();
        Thread.sleep(3000);
        editBoxOTP.sendKeys(OTP);
    }

    public void clickOnConfirmButton() {
        clickOnConfirm.click();
    }

    public void clickOnLetsGo() throws Exception {
        clickOnLetsHaveLook.click();
        Thread.sleep(3000);
        for (int i = 0; i <= 1; i++) {
            clickOnAllow.click();
        }
    }

    public void clickOnTermsConditions() throws Exception {
        Thread.sleep(3000);
        clickOnAgreeTerms.click();
    }

    public void clickOnContinue() throws Exception {
        continueButton.click();
        guideScreen.click();
        Thread.sleep(5000);
    }

    public void isLoginPageDisplayed() throws Exception {

        isElementPresent(loginPageHeader);
    }

    public void isHousekeepingPageDisplayed() {
        pageAfterLoginSecurly.isDisplayed();
    }

    public void selectLanguage() throws Exception {
        Thread.sleep(2000);
        if (isElementPresent(lnk_Language)) {
            lnk_Language.click();
        } else {
            System.out.println("language page is not showing");
        }
    }

    public WebElement waitForElementToAppear(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.visibilityOf(id));
        return id;
    }
    public String captureScreenshot(AppiumDriver<MobileElement> driver, String testName) throws IOException {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String screenshotDirectory = projectDirectory + "/screenshots/";

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotFileName = testName + "_" + timestamp + ".png";

        String screenshotPath = screenshotDirectory + screenshotFileName;

        FileUtils.copyFile(srcFile, new File(screenshotPath));

        return screenshotPath;
    }

    private static Map<String, Object> readYamlFile(String yamlFilePath) {
        try (InputStream input = new FileInputStream(yamlFilePath)) {
            Yaml yaml = new Yaml();
            return yaml.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isElementPresent(WebElement locator) {
        try {
            waitForElementToAppear(locator);
            if (locator.isDisplayed()) System.out.println("Element present on screen ***********" + locator);
            return true;
        } catch (Exception e) {
            System.out.println("Element not present on screen **************" + locator);
            return false;
        }
    }


    @Test
    public void testLogin() throws Exception {


        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(projectDirectory+"\\reports\\extentReport\\ExtentReport.html");
        extent = new ExtentReports();
//        test = extent.createTest("Live App Status");
        extent.attachReporter(htmlReporter);
        JSONArray testData = readTestData(projectDirectory+"//src//main//resources//test-data.json");

        for (Object obj : testData) {
            JSONObject data = (JSONObject) obj;
            String appPackage = (String) data.get("appPackage");

            String appActivity = (String) data.get("appActivity");

            String mobileNumber = (String) data.get("mobileNumber");
            String otp = (String) data.get("otp");

            // Set up desired capabilities
//            DesiredCapabilities caps = new DesiredCapabilities();
//            caps.setCapability("platformName", "Android");
//            caps.setCapability("platformVersion", "13.0");
//            caps.setCapability("deviceName", "pvq8gmjz75j7fu6p");
//            caps.setCapability("automationName", "UiAutomator2");
//            caps.setCapability("appPackage", appPackage);
//            caps.setCapability("appActivity", appActivity);
//            caps.setCapability("log", projectDirectory+"reports\\appiumLogs\\appium.log");
//            caps.setCapability("log-level", "debug");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "iOS");
            caps.setCapability("platformVersion", "16.4");
            caps.setCapability("deviceName", "iPhone 11");
            caps.setCapability("automationName", "XCUITest");
            caps.setCapability("udid", "00008030-000139691A42802E");
            caps.setCapability("bundleId", "com.LycaMobile.UK");
            caps.setCapability("log", projectDirectory+"reports\\appiumLogs\\appium.log");
            caps.setCapability("log-level", "debug");

            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
            System.out.println(appActivity);
            System.out.println(appPackage);
            System.out.println(mobileNumber);
            System.out.println(otp);
            ExtentTest test = extent.createTest("Test Case: " + appPackage);
            try {
                Thread.sleep(2000);
                selectLanguage();
                Thread.sleep(3000);
                isLoginPageDisplayed();
                Thread.sleep(1000);
                enterMobileNumber(mobileNumber);
                test.log(Status.PASS, "Entered mobile number: " + mobileNumber);
                Thread.sleep(1000);
                clickOnSignInButton();
                test.log(Status.PASS, "clicked on signin button");
                Thread.sleep(4000);
                enterOTP(otp);
                test.log(Status.PASS, "Entered otp number: " + otp);
                Thread.sleep(1000);
                clickOnConfirmButton();
                test.log(Status.PASS, "clicked on confirm button");
                isHousekeepingPageDisplayed();
                test.log(Status.PASS, "house keeping page displayed");
                clickOnLetsGo();
                test.log(Status.PASS, "lets go page displayed");
                clickOnTermsConditions();
                test.log(Status.PASS, "terms and condition page displayed");
                clickOnContinue();
                test.log(Status.PASS, "app is working perfectly");
                Thread.sleep(5000);
            } catch (Exception e) {
                // Log the failure and continue with the next iteration
                test.log(Status.FAIL, "Test case failed: " + e.getMessage());
                String logContents = driver.manage().logs().get("logcat").getAll().toString();
                String screenshotPath = captureScreenshot(driver, "LiveTest");
                test.fail("Logs:\n" + logContents);
                test.addScreenCaptureFromPath(screenshotPath);
            } finally {
                driver.quit();
                extent.flush();
            }
        }

    }
}
