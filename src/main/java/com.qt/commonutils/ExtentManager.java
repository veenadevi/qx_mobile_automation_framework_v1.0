package com.qt.commonutils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qt.config.Global;
import com.qt.commonutils.AppiumManager;
import com.qt.commonutils.GlobalSession;


/**
 * This Class has all the Objects related to ExtentManager.
 *
 * @author
 */
public class ExtentManager {


	private static ExtentReports extent;
	/**
	 * Retrieves the ExtentReports instance.
	 *
	 * @return The ExtentReports instance.
	 */

	public static ExtentReports getExtent() {
		if (extent == null) {
			try {
				extent = new ExtentReports();
				extent.attachReporter(getHtmlReporter());
				return extent;
			}catch (Exception e) {
				System.out.println("Exception while creating report html file.");
			}
		}
		return extent;
	}

	/**
	 * Retrieves the HTML reporter for Extent Reports.
	 *
	 * @return the ExtentHtmlReporter instance
	 * @throws IOException if an I/O error occurs
	 */
	private static ExtentHtmlReporter getHtmlReporter() throws IOException {
//		String reportPath = getReportPath();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Global.getReportPath() + File.separator + "report.html");

		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/main/resources/extent.xml");
		htmlReporter.config().setDocumentTitle(Global.getConfig().getProperties().getReportName());
		htmlReporter.config().setReportName(Global.getConfig().getProperties().getReportName());
		htmlReporter.config().setTheme(Theme.STANDARD);
		return htmlReporter;
	}

//	private static String getReportPath(){
//
////		String folderName = "Report_" + dateFormat.format(date);
//		String reportName = "report.html";
//
//		GlobalSession.setGlobalSession(new GlobalSession());
//		GlobalSession.getGlobalSession().setReportPath(reportPath);
//
//
////		File file = new File(reportPath + File.separator + folderName);
////		file.mkdir();
//		setDeviceReportPath();
//		return reportPath + File.separator + reportName;
//	}
//
//	public static  void setDeviceReportPath(){
//		for(AppiumManager appiumManager: Global.getAppiumManagerList()){
//			String deviceName = appiumManager.getDeviceName();
//			String folderName = deviceName + "_" + dateFormat.format(date);
//			File file = new File(reportPath + File.separator + folderName);
//			file.mkdir();
//
//			appiumManager.setReportPath(file.getPath());
//		}
//	}
}
