package com.qt.commonutils;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;


/**
 * This Class has all the Objects related to QXClient.
 *
 * @author
 */
public class QXClient {

    private AppiumDriver appiumDriver;
    private QXReport qxReport;
    private Gestures gestures;
    private WaitForUtils waitForUtils;
    private ExcelUtils excelUtils;
    private PropUtils clientUtils;
    private ScreenshotUtils screenshotUtils;
    private AssertUtils assertUtils;
    private LogUtils logUtils;
    private GSheetObject gSheetObject;

    /**
     * Constructs a QXClient instance with the specified AppiumDriver.
     *
     * @param appiumDriver The AppiumDriver instance associated with the QXClient.
     */

    public QXClient(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    /**
     * Retrieves the Gestures object associated with the QXClient.
     * If the Gestures object has not been instantiated yet, it creates a new one.
     *
     * @return The Gestures object associated with the QXClient.
     */

    public Gestures gestures(){
        if(this.gestures == null){
            this.gestures = new Gestures(this.appiumDriver);
        }

        return this.gestures;
    }
    /**
     * Returns an instance of WaitForUtils if it's not already initialized, otherwise returns the existing instance.
     *
     * @return The instance of WaitForUtils.
     */

    public WaitForUtils waitUtils(){
        if(this.waitForUtils == null){
            this.waitForUtils = new WaitForUtils(this.appiumDriver);
        }

        return this.waitForUtils;
    }
    /**
     * Returns the instance of the AppiumDriver associated with this class.
     *
     * @return The instance of AppiumDriver.
     */

    public AppiumDriver driver(){
        return this.appiumDriver;
    }

    /**
     * Returns the instance of ExcelUtils associated with this class.
     * If the instance is not already created, it initializes a new one.
     *
     * @return The instance of ExcelUtils.
     */
    public ExcelUtils excelUtils(){
        if(this.excelUtils == null){
            this.excelUtils = new ExcelUtils();
        }
        return this.excelUtils;
    }
    /**
     * Returns the instance of PropUtils associated with this class.
     * If the instance is not already created, it initializes a new one.
     *
     * @return The instance of PropUtils.
     */

    public PropUtils propUtils(){
        if(this.clientUtils == null){
            this.clientUtils = new PropUtils();
        }

        return this.clientUtils;
    }

    /**
     * Returns the instance of ScreenshotUtils associated with this class.
     * If the instance is not already created, it initializes a new one.
     *
     * @return The instance of ScreenshotUtils.
     */

    public ScreenshotUtils screenshotUtils(){
        if(this.screenshotUtils == null){
            this.screenshotUtils = new ScreenshotUtils(this.appiumDriver);
        }

        return screenshotUtils;
    }

    /**
     * Returns the instance of AssertUtils associated with this class.
     * If the instance is not already created, it initializes a new one.
     *
     * @return The instance of AssertUtils.
     */

    public AssertUtils getAssertUtils(){
        if(this.assertUtils == null){
            this.assertUtils = new AssertUtils();
        }

        return this.assertUtils;
    }

    /**
     * Returns the instance of GSheet associated with the provided sheetId.
     * If the instance is not already created, it initializes a new one.
     *
     * @param sheetId The ID of the Google Sheet.
     * @return The instance of GSheet.
     * @throws GeneralSecurityException If there is a security-related error.
     * @throws IOException              If an I/O error occurs.
     */

    public GSheet GSheet(String sheetId) throws GeneralSecurityException, IOException {
        if(this.gSheetObject == null){
            this.gSheetObject = new GSheetObject();
        }

        return this.gSheetObject.getGSheet(sheetId);
    }

    /**
     * Returns the ExtentTest instance associated with the current test.
     *
     * @return The ExtentTest instance.
     */

    public ExtentTest report(){
        return QXReport.getTest();
    }


    /**
     * Returns the logger instance associated with the current test.
     *
     * @return The logger instance.
     */
    public Logger logger(){
        return GlobalSession.get().getLogUtils().getLogger();
    }



    private static ThreadLocal<QXClient> qualitrix = new ThreadLocal<>();

    /**
     * Sets the QXClient instance for the current thread.
     *
     * @param qx The QXClient instance.
     */
    public static void setQX(QXClient qx) {
        qualitrix.set(qx);
    }


    /**
     * Returns the QXClient instance associated with the current thread.
     *
     * @return The QXClient instance.
     */
    public static QXClient get(){
        return qualitrix.get();
    }
}
