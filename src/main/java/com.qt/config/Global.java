package com.qt.config;

import com.google.gson.Gson;

import com.qt.commonutils.AppiumManager;
import com.qt.commonutils.GlobalSession;
import com.qt.config.Config;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.WebElement;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This Class has all the Objects related to Global.
 *
 * @author
 */
public class Global {

    private static Config config;
    private static String reportPath;

    private static List<AppiumManager> appiumManagerList = new ArrayList<AppiumManager>();

    /**
     * Sets the configuration for the application using the provided configuration JSON.
     *
     * @param configJSON The configuration JSON string.
     */
    /**
     * Sets the configuration based on the provided JSON string.
     * @param configJSON The JSON string representing the configuration.
     */
    public static void setConfig(String configJSON){
        config = new Config(configJSON);
    }
    /**
     * Retrieves the current configuration.
     * @return The current configuration.
     */
    public static Config getConfig(){
        return  config;
    }

    /**
     * Adds an AppiumManager instance to the list of Appium managers.
     * @param appiumManager The AppiumManager instance to add.
     */
    public synchronized  static void addAppiumManagerToList(AppiumManager appiumManager){
        appiumManagerList.add(appiumManager);
        System.out.println("size is"+appiumManagerList.size());


    }
    /**
     * Retrieves the list of AppiumManager instances.
     * @return The list of AppiumManager instances.
     */

    public synchronized  static List<AppiumManager> getAppiumManagerList(){
        System.out.println("list size***"+appiumManagerList);
        return appiumManagerList;
    }

    /**
     * Sets the path for the report.
     * @param reportPath The path for the report.
     */

    public static void setReportPath(String reportPath) {
        Global.reportPath = reportPath;
    }
    /**
     * Retrieves the path for the report.
     * @return The path for the report.
     */
    public static String getReportPath() {
        return reportPath;
    }


    /**
     * Retrieves an available AppiumManager device from the list.
     * @return An available AppiumManager device, or null if none are available.
     */
    public static synchronized AppiumManager getAvailableDevice(){
        for (AppiumManager appiumManager1 : Global.getAppiumManagerList()) {
            if (appiumManager1.isAvailable()) {
                System.out.println("divise");

                appiumManager1.setAvailable(false);
                return appiumManager1;
            }
        }
        return null;
    }
}
