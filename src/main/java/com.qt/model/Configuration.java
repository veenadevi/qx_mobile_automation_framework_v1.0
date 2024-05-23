package com.qt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;





/**
 * This Class has all the Objects related to Configuration.
 *
 * @author
 */
public class Configuration {
    /**
     * The properties object.
     */
    @SerializedName("properties")
    @Expose

    private Properties properties;
    @SerializedName("server")
    @Expose
    private Server server;
    @SerializedName("capabilities")
    @Expose
    private Capabilities capabilities;
    @SerializedName("devices")
    @Expose

    private List<Device> devices = null;

    /**
     * Gets the properties object.
     *
     * @return The properties object.
     */

    public Properties getProperties() {
        return properties;
    }


    /**
     * setters and geters mehod related to configuration
     *
     *
     */

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
