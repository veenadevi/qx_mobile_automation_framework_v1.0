package com.qt.commonutils;

import java.io.BufferedWriter;
import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * This Class has all the Objects related to QXReport.
 *
 * @author
 */

public class QXReport {

    /** ThreadLocal to hold ExtentTest instances for each thread. */
    private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

    /**
     * Returns the ExtentTest instance associated with the current thread.
     *
     * @return The ExtentTest instance.
     */
    public static synchronized ExtentTest getTest() {
        return testReport.get();
    }

    /**
     * Sets the ExtentTest instance for the current thread.
     *
     * @param extent The ExtentTest instance.
     */
    public static synchronized void setTestReport(ExtentTest extent) {
        testReport.set(extent);
    }
}

