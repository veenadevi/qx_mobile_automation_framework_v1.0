package com.qt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * This Class has all the Objects related to Server.
 *
 * @author
 */

public class Server {

    /**
     * The value of the "host" field.
     */

    @SerializedName("host")
    @Expose
    private String host;

    /**
     * Gets the host.
     *
     * @return The host.
     */
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
