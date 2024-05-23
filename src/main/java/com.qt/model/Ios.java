package com.qt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * This Class has all the Objects related to Ios.
 *
 * @author
 */

public class Ios {
    /**
     * The value of the "bundleId" field.
     */

    @SerializedName("bundleId")
    @Expose
    private String bundleId;
    @SerializedName("browserName")
    @Expose
    private String browserName;

    /**
     * Gets the bundle ID.
     *
     * @return The bundle ID.
     */

    public String getBundleId() {
        return bundleId;
    }
    /**
     *
     *setter and getter method related to ios
     *
     */


    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }
}
