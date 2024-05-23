package com.qt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This Class has all the Objects related to Properties.
 *
 * @author
 */
public class Properties {

    /**
     * The value of the "environment" field.
     */

    @SerializedName("environment")
    @Expose
    private String environment;

    @SerializedName("reportName")
    @Expose
    private String reportName;


    /**
     * Gets the environment.
     *
     * @return The environment.
     */

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }
}
