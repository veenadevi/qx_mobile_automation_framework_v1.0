package com.qt.commonutils;

import com.qt.config.Global;
import com.qt.commonutils.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.IOSServerFlag;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

/**
 * Manages the Appium server and creates Appium drivers for testing mobile applications.
 * @author
 */
public class AppiumManager {


    /**
     * Represents the Appium properties
     */
    private final String IP_ADDRESS = "127.0.0.1";
    private AppiumDriverLocalService appiumDriverLocalService;
    private URL endpoint;
    private boolean isAvailable;
    private DesiredCapabilities desiredCapabilities;
    private String deviceName;
    private String version;
    private String platform;
    private String reportPath;
    private AppiumDriver driver;
    private boolean isAndroid;
    private boolean isIOS;
    private boolean isNative;
    private boolean isBrowser;
    private String automationType;

    /**
     * Constructor for the AppiumManager class.
     * Initializes an instance with the provided device information and automation type.
     *
     * @param deviceName     The name of the device.
     * @param version        The version of the device.
     * @param platform       The platform of the device (android or ios).
     * @param automationType The automation type (native or browser).
     */

    public AppiumManager(String deviceName, String version, String platform, String automationType){
        this.deviceName = deviceName;
        this.version = version;
        this.isAvailable = true;
        this.platform = platform;
        this.automationType = automationType;
        this.setAutomationType();
    }

    /**
     * Sets the automation type flags based on the provided platform and automation type.
     */
    private void setAutomationType(){
        if(this.platform.equalsIgnoreCase("android"))
            this.isAndroid = true;
        else if(this.platform.equalsIgnoreCase("ios"))
            this.isIOS = true;

        if(this.automationType.equalsIgnoreCase("native"))
            this.isNative = true;
        else if(this.automationType.equalsIgnoreCase("browser"))
            this.isBrowser = true;

    }

    /**
     * Starts the Appium server.
     *
     * @return The endpoint URL of the Appium server.
     * @throws Exception If an error occurs while starting the server.
     */


    public URL startAppiumServer() throws Exception {
        String logPath = this.reportPath + File.separator + "logs" + File.separator + "appium_log_" + System.currentTimeMillis() + ".txt";
        File logFile = new File(logPath);
        logFile.createNewFile();

        String bootStrapPort = Integer.toString(AppiumManagerUtils.getFreePort());
        String browserPort = Integer.toString(AppiumManagerUtils.getFreePort());
        int serverPort = AppiumManagerUtils.getFreePort();
        File fileJS = new File(AppiumManagerUtils.getAppiumJSPath());
        File nodeJSPath = new File(AppiumManagerUtils.getNodePath());

        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withAppiumJS(fileJS);
        appiumServiceBuilder.usingDriverExecutable(nodeJSPath);
        appiumServiceBuilder.withIPAddress(IP_ADDRESS);
        appiumServiceBuilder.usingPort(serverPort);
        appiumServiceBuilder.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, bootStrapPort);
        appiumServiceBuilder.withLogFile(logFile);

        if(this.isIOS){
            appiumServiceBuilder.withArgument(IOSServerFlag.WEBKIT_DEBUG_PROXY_PORT, browserPort);
        }else if(this.isAndroid){
            appiumServiceBuilder.withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, browserPort);
        }

        return this.startServer(appiumServiceBuilder);
    }

    /**
     * Starts the Appium server.
     *
     * @return The endpoint URL of the Appium server.
     * @throws Exception If an error occurs while starting the server.
     */
    public URL startServer(AppiumServiceBuilder appiumServiceBuilder) throws Exception {
        this.appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        this.appiumDriverLocalService.start();
        this.appiumDriverLocalService.clearOutPutStreams();

        if (this.appiumDriverLocalService.isRunning()) {
            System.out.println("Appium Server Started");
            this.endpoint = this.appiumDriverLocalService.getUrl();
            return this.endpoint;
        } else {
            System.out.println("Server startup failed");
            throw new SessionNotCreatedException("");
        }
    }

    /**
     * Stops the Appium server.
     */
    public void stopAppiumServer(){
        this.appiumDriverLocalService.stop();
    }

    /**
     * Returns the endpoint URL.
     *
     * @return The endpoint URL.
     */
    public URL getEndpoint(){
        return this.endpoint;
    }
    /**
     * Sets the endpoint URL.
     *
     * @param endpoint The endpoint URL to be set.
     */

    public void setEndpoint(URL endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Creates a driver instance based on configuration.
     *
     * @throws Exception If an error occurs during driver creation.
     */
    public void createDriver() throws Exception {
        System.out.println("Create Driver...");
        System.out.println(Global.getConfig().isLocal());
        if(Global.getConfig().isLocal())
        {
            System.out.println("Entering Set Caps Local Execution....");
            this.setLocalCapabilities();
        }
        else{
            System.out.println("Entering Set Caps Cloud Execution....");
            this.setCloudCapabilities();
        }
        this.setDriver();
    }
    /**
     * Sets desired capabilities for local execution.
     *
     * @return The desired capabilities set for local execution.
     * @throws Exception If an error occurs during capability setting.
     */

    private DesiredCapabilities setLocalCapabilities() throws Exception {
        System.out.println("setting capabilities");
        System.out.println("setting isAndroid: " + isAndroid);
        if(this.isAndroid) {
            this.desiredCapabilities = new DesiredCapabilities(Global.getConfig().getCapabilityMap("android"));
            desiredCapabilities.setCapability("systemPort", AppiumManagerUtils.getFreePort());
            if(AppiumManagerUtils.getMajorVersion(version) >=6)
                desiredCapabilities.setCapability("automationName", "uiautomator2");
            else
                desiredCapabilities.setCapability("automationName", "uiautomator1");
        }else {
            this.desiredCapabilities = new DesiredCapabilities(Global.getConfig().getCapabilityMap("ios"));
            desiredCapabilities.setCapability("wdaLocalPort", AppiumManagerUtils.getFreePort());
            if(AppiumManagerUtils.getMajorVersion(version) >= 9)
                desiredCapabilities.setCapability("automationName", "XCUITest");
        }

        this.desiredCapabilities.setCapability("platformVersion", this.version);

        if(Global.getConfig().isLocal()){
            desiredCapabilities.setCapability("deviceName", this.deviceName);
            desiredCapabilities.setCapability("udid", this.deviceName);
        }
        desiredCapabilities.setCapability("app","D:\\Qualitrix\\Automation Initiative\\QTX_Base_Frameworks\\MOBILE\\qx_mobile_automation_framework_v1.0\\caps\\ContactManager.apk");
        return desiredCapabilities;
    }
    /**
     * Sets desired capabilities for cloud execution.
     *
     * @return The desired capabilities set for cloud execution.
     * @throws Exception If an error occurs during capability setting.
     */
    private DesiredCapabilities setCloudCapabilities() throws Exception {
        this.desiredCapabilities = new DesiredCapabilities(Global.getConfig().getCapabilityMap("cloudCapabilities"));
        System.out.println(Arrays.asList(Global.getConfig().getCapabilityMap("cloudCapabilities")));
//        desiredCapabilities.setCapability("build","Lycaa Android");
//        desiredCapabilities.setCapability("name","Android Samsung Galaxy S22");
//        desiredCapabilities.setCapability("platformName", "android");
//        desiredCapabilities.setCapability("deviceName", "Galaxy S22 5G");
//        desiredCapabilities.setCapability("platformVersion", "13");
//        desiredCapabilities.setCapability("app", "lt://APP10160612541701335219010523");
//        desiredCapabilities.setCapability("video", true);
//        desiredCapabilities.setCapability("isRealMobile", true);

        return desiredCapabilities;
    }
    /**
     * Sets the driver based on the configuration.
     *
     * @throws MalformedURLException If the URL is malformed.
     */
    private void setDriver() throws MalformedURLException {
        if(this.isAndroid){
            if(Global.getConfig().isLocal()){
                System.out.println("Entering Set Driver Local Execution....");
                this.driver = new AndroidDriver (endpoint, this.desiredCapabilities);
            }
            else{
                System.out.println("Entering Set Driver Cloud Execution....");
                Map<String, Object> serverDetails = Global.getConfig().getCapabilityMap("cloud_server");
                String serverURL = "https://" + serverDetails.get("userName") + ":" + serverDetails.get("accessKey") + serverDetails.get("host");
                System.out.println(serverURL);
                this.endpoint = new URL(serverURL);
                this.driver = new AndroidDriver(endpoint, this.desiredCapabilities);
            }
            AppiumManager.setDriverToThreadLocal(this.driver);
        }else if(this.isIOS){
            this.driver = new IOSDriver (endpoint, this.desiredCapabilities);
            AppiumManager.setDriverToThreadLocal(this.driver);
        }
    }
    /**
     * Thread-local variable to store the Appium driver instance.
     */
    private static ThreadLocal<AppiumDriver<?>> appiumDriver = new ThreadLocal<AppiumDriver<?>>();

    /**
     * Sets the Appium driver instance to the thread-local variable.
     *
     * @param driver The Appium driver instance to set.
     */
    public static void setDriverToThreadLocal(AppiumDriver<?> driver) {
        appiumDriver.set(driver);
    }

    /**
     * Retrieves the Appium driver instance from the thread-local variable.
     *
     * @return The Appium driver instance.
     */

    public static AppiumDriver<?> getAppiumDriver(){
        return appiumDriver.get();
    }

    /**
     * Sets the availability status.
     *
     * @param available The availability status to set.
     */

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    /**
     * Checks if the resource is available.
     *
     * @return True if the resource is available, otherwise false.
     */
    public boolean isAvailable() {
        return isAvailable;
    }
    /**
     * Retrieves the desired capabilities.
     *
     * @return The desired capabilities.
     */

    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    /**
     * Retrieves the device name.
     *
     * @return The device name.
     */

    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the report path.
     *
     * @param reportPath The report path to set.
     */
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    /**
     * Retrieves the report path.
     *
     * @return The report path.
     */

    public String getReportPath() {
        return reportPath;
    }

    /**
     * Checks if the platform is Android.
     *
     * @return True if the platform is Android, otherwise false.
     */

    public boolean isAndroid() {
        return isAndroid;
    }


    /**
     * Checks if the platform is iOS.
     *
     * @return True if the platform is iOS, otherwise false.
     */
    public boolean isIOS() {
        return isIOS;
    }

    /**
     * Checks if the application is native.
     *
     * @return True if the application is native, otherwise false.
     */

    public boolean isNative() {
        return isNative;
    }

    /**
     * Checks if the application is a browser.
     *
     * @return True if the application is a browser, otherwise false.
     */

    public boolean isBrowser() {
        return isBrowser;
    }

    /**
     * Retrieves the platform.
     *
     * @return The platform.
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Quits the driver if it's not null.
     */
    public void quitDriver(){
try {
    if (this.driver != null) {
        this.driver.quit();
    }
}catch (Exception e){}
    }
}
