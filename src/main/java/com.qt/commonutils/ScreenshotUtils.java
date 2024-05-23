package com.qt.commonutils;

import com.qt.config.Global;
import com.qt.commonutils.*;
import com.qt.commonutils.GlobalSession;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * This Class has all the Objects related to ScreenshotUtils.
 *
 * @author
 */

public class ScreenshotUtils {

    AppiumDriver driver;

    /**
     * Constructs a new ScreenshotUtils instance with the specified AppiumDriver.
     *
     * @param driver The AppiumDriver instance.
     */
    public ScreenshotUtils(AppiumDriver driver){
        this.driver = driver;
    }

    /**
     * Captures a screenshot and returns it as a Base64 encoded string.
     *
     * @return The Base64 encoded string representing the screenshot.
     */

    public String captureBase64(){
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot;
    }
    /**
     * Captures a screenshot and saves it to a file.
     *
     * @return The captured screenshot file.
     * @throws IOException If an I/O error occurs while saving the screenshot.
     */

    public File captureFile() throws IOException {
        System.out.println("GlobalSession.get(): " + GlobalSession.get());
        System.out.println("AppiumManger: " + GlobalSession.get().getAppiumManager());
        String path = GlobalSession.get().getAppiumManager().getReportPath() + File.separator + "screenshots" + File.separator + UUID.randomUUID().toString() + ".jpg";
        System.out.println("@Path: " + path);
        return this.captureFile(path);
    }

    /**
     * Captures a screenshot and saves it to the specified file path.
     *
     * @param filePath The file path where the screenshot should be saved.
     * @return The captured screenshot file.
     * @throws IOException If an I/O error occurs while saving the screenshot.
     */
    public File captureFile(String filePath) throws IOException {
        return this.captureFile(new File(filePath));
    }

    /**
     * Captures a screenshot and saves it to the specified file.
     *
     * @param file The file where the screenshot should be saved.
     * @return The captured screenshot file.
     * @throws IOException If an I/O error occurs while saving the screenshot.
     */
    public File captureFile(File file) throws IOException {
        File screenshotFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, file);
        return file;
    }
}