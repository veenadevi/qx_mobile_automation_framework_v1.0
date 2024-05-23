package com.qt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * This Class has all the Objects related to Device.
 *
 * @author
 */

public class Device {
    /**
     * The value of the "name" field.
     */

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("platform")
    @Expose
    private String platform;

    /**
     * Gets the name.
     *
     * @return The name.
     */


    public String getName() {
        return name;
    }

    /**
     *
     *setters and getters method related to Device
     *
     */


    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}

