package com.qt.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;





/**
 * This Class has all the Objects related to Capabilities.
 *
 * @author
 */
public class Capabilities {
    /**
     * Represents the configuration specific to Android.
     */


    @SerializedName("android")
    @Expose
    private Android android;
    @SerializedName("ios")
    @Expose
    private Ios ios;

    /**
     * Gets the Android-specific configuration.
     *
     * @return The Android-specific configuration.
     */
    public Android getAndroid() {
        return android;
    }

    public void setAndroid(Android android) {
        this.android = android;
    }

    public Ios getIos() {
        return ios;
    }

    public void setIos(Ios ios) {
        this.ios = ios;
    }
}