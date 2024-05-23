package com.qt.commonutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qt.annotation.values.Author;
import com.aventstack.extentreports.Status;


/**
 * This Class has all the Objects related to ExtentTestManager.
 *
 * @author
 */
public class ExtentTestManager {

	private static ExtentTest extentTtest;
	public static ExtentReports extentReports;

	/**
	 * Initializes the ExtentReports instance.
	 */

	public static void InitReport(){
		extentReports = ExtentManager.getExtent();
	}

	/**
	 * Creates a synchronized ExtentTest with the specified name, description, and device ID.
	 *
	 * @param name        the name of the test
	 * @param description the description of the test
	 * @param deviceId    the ID of the device
	 * @return the ExtentTest instance
	 */

	public synchronized static ExtentTest createTest(String name, String description, String deviceId) {
		extentTtest = extentReports.createTest(name, description).assignCategory(deviceId);
		return extentTtest;
	}

	/**
	 * Creates a synchronized ExtentTest with the specified name and device ID.
	 *
	 * @param name     the name of the test
	 * @param deviceId the ID of the device
	 * @return the ExtentTest instance
	 */


	public synchronized static ExtentTest createTest(String name, String deviceId) {
		extentTtest = extentReports.createTest(name).assignCategory(deviceId);
		return extentTtest;
	}

	/**
	 * Creates a synchronized ExtentTest with the specified name, device ID, and author.
	 *
	 * @param name     the name of the test
	 * @param deviceId the ID of the device
	 * @param author   the author of the test
	 * @return the ExtentTest instance
	 */


	public synchronized static ExtentTest createTest(String name, String deviceId, Author author) {
		extentTtest = extentReports.createTest(name).assignCategory(deviceId).assignAuthor(author.name());
		return extentTtest;
	}
	/**
	 * Logs a pass status with the specified log message.
	 *
	 * @param log the log message
	 */

	public static void logPass(String log) {
		extentTtest.pass(log);
	}

	/**
	 * Logs a fail status with the specified log message.
	 *
	 * @param log the log message
	 */

	public static void logFail(String log) {
		extentTtest.fail(log);
	}

	/**
	 * Logs an error status with the specified log message and throwable.
	 *
	 * @param log       the log message
	 * @param throwable the throwable representing the error
	 */

	public static void logError(String log, Throwable throwable) {
		extentTtest.fail(log);
		extentTtest.log(Status.FAIL, "Error details:");
	}

	/**
	 * Logs an informational message.
	 *
	 * @param log the information log message
	 */
	public static void logInfo(String log) {
		extentTtest.info(log);
	}

	/**
	 * Logs a screenshot to the Extent report.
	 *
	 * @param screenshotPath the path of the screenshot file
	 * @throws if an I/O error occurs while reading the screenshot file
	 */

	public static void logScreenshot(String screenshotPath) {
		extentTtest.addScreenCaptureFromPath(screenshotPath);
	}

}

