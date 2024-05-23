package com.qt.config;

import com.google.gson.Gson;
import com.qt.config.*;
import com.qt.model.Capabilities;
import com.qt.model.Configuration;
import com.qt.model.Device;
import com.qt.model.Properties;
import com.qt.model.Server;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * This Class has all the Objects related to Config.
 *
 * @author
 */
public class Config {

    private final Configuration configuration;
    private String configJSON;
    private boolean isLocal;
    private boolean isAndroid;
    private boolean isIOS;
    private boolean isNative;
    private boolean isBrowser;

    /**
     * Constructs a Config object with the provided configuration JSON.
     *
     * @param configJSON The configuration JSON string.
     */

    public  Config(String configJSON){
        this.configJSON = configJSON;
        this.configuration = new Gson().fromJson(configJSON, Configuration.class);

        if(this.configuration.getProperties().getEnvironment().equalsIgnoreCase("local")){
            this.isLocal = true;
        }
    }
    /**
     * Returns whether the test execution is happening on a local environment.
     * @return true if the execution is local, otherwise false.
     */
    public boolean isLocal() {
        return isLocal;
    }
    /**
     * Returns whether the test execution is happening on an Android device.
     * @return true if the execution is on an Android device, otherwise false.
     */
    public boolean isAndroid() {
        return isAndroid;
    }
    /**
     * Returns whether the test execution is happening on an iOS device.
     * @return true if the execution is on an iOS device, otherwise false.
     */
    public boolean isIOS() {
        return isIOS;
    }
    /**
     * Returns whether the application under test is a native mobile app.
     * @return true if the application is native, otherwise false.
     */
    public boolean isNative() {
        return isNative;
    }
    /**
     * Returns whether the application under test is a web browser.
     * @return true if the application is a browser, otherwise false.
     */
    public boolean isBrowser() {
        return isBrowser;
    }
    /**
     * Retrieves the capabilities set for the test execution.
     * @return Capabilities object representing the test capabilities.
     */

    public Capabilities getCapabilities(){
        return this.configuration.getCapabilities();
    }
    /**
     * Retrieves the server configuration for the test execution.
     * @return Server object representing the server configuration.
     */
    public Server getServer(){
        return this.configuration.getServer();
    }
    /**
     * Retrieves the JSON string representing the test configuration.
     * @return String containing the test configuration in JSON format.
     */
    public String getConfigJSON() {
        return configJSON;
    }

    /**
     * Retrieves the capability map based on the specified platform.
     * @param platform The platform for which capability map is to be retrieved.
     * @return Map containing the capabilities for the specified platform.
     */
    public Map<String, Object> getCapabilityMap(String platform){
        String configJSON = Global.getConfig().getConfigJSON();
        System.out.print("configJSON: " +configJSON);
        JSONObject jsonObject = new JSONObject(configJSON);

        if(platform.equalsIgnoreCase("android")){
            JSONObject capObject = jsonObject.getJSONObject("capabilities").getJSONObject("android");
            return capObject.toMap();
        }else if(platform.equalsIgnoreCase("ios")){
            JSONObject capObject = jsonObject.getJSONObject("capabilities").getJSONObject("ios");
            return capObject.toMap();
        }
        else{
            JSONObject capObject = jsonObject.getJSONObject(platform);
            return capObject.toMap();
        }
    }

    public List<Device> getDevices(){
        return this.configuration.getDevices();
    }

    public Properties getProperties(){
        return this.configuration.getProperties();
    }
}