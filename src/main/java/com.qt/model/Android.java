package com.qt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * This Class has all the Objects related to Android.
 *
 * @author
 */
public class Android {

    /**
     * The package name of the application.
     */
    @SerializedName("appPackage")
    @Expose

    private String appPackage;
    @SerializedName("appActivity")
    @Expose
    private String appActivity;
    @SerializedName("browserName")
    @Expose
    private String browserName;
    /**
     * Gets the package name of the application.
     *
     * @return The package name of the application.
     */

    /**
     *setter and getter methods for related andriod device
     *
     *
     */

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }
}
