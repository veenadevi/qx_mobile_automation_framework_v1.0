package com.qt.commonutils;

import com.qt.commonutils.LogUtils;


import java.io.File;
/**
 * This Class has all the Objects related to GlobalSession.
 *
 * @author
 */
public class GlobalSession {

    private String reportPath;
    private String screenshotPath;
    private String logPath;
    private LogUtils logUtils;

    /**
     * ThreadLocal variable to store the GlobalSession object.
     */

    private static ThreadLocal<GlobalSession> globalSession = new ThreadLocal<GlobalSession>();

    /**
     * Sets the global session.
     *
     * @param globalSession The global session to set.
     */
    public static void set(GlobalSession globalSession) {
        GlobalSession.globalSession.set(globalSession);
    }

    /**
     * Gets the global session.
     *
     * @return The global session.
     */
    public static GlobalSession get(){
        return globalSession.get();
    }

    private AppiumManager appiumManager;

    /**
     *
     *setter and geeter methods for set and fetch the values
     */

    public void setAppiumManager(AppiumManager appiumManager) {
        this.appiumManager = appiumManager;
    }

    public AppiumManager getAppiumManager(){
        return this.appiumManager;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogPath() {
        return logPath;
    }

    /**
     * Retrieves the LogUtils instance.
     *
     * @return The LogUtils instance.
     */

    public LogUtils getLogUtils(){
        if(this.logUtils == null){
            this.logUtils = new LogUtils(GlobalSession.get().getAppiumManager().getReportPath() + File.separator + "logs" + File.separator + "execution.log");
        }
        return this.logUtils;
    }
}
