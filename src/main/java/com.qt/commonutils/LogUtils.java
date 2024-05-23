package com.qt.commonutils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.Closeable;
import java.io.IOException;

/**
 * This Class has all the Objects related to LogUtils.
 *
 * @author
 */
public class LogUtils implements Closeable {
    private final String path;
    FileAppender appender;

    /**
     * Constructs a LogUtils instance with the specified log file path.
     * Initializes a file appender with the given file path and log pattern.
     *
     * @param path The path to the log file.
     */


    public LogUtils(String path) {
        this.path = path;
        // Create file appender
        this.appender = new FileAppender();
        appender.setFile(path);
        appender.setLayout(new PatternLayout("%d [%t] %-5p %c - %m%n"));
        appender.activateOptions();
    }
    /**
     * Retrieves a Logger instance with a unique name derived from the calling class, method, and line number.
     * The Logger is configured to write logs to the specified file path.
     *
     * @return A Logger instance configured to write logs to the specified file path.
     */
    public Logger getLogger(){
        StackTraceElement myCaller = Thread.currentThread().getStackTrace()[3];
        String className = myCaller.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1, className.length());
        String methodName = myCaller.getMethodName();
        int lineNo = myCaller.getLineNumber();

        Logger logger = Logger.getLogger(className +"-"+ methodName +"-"+ lineNo);
        logger.setAdditivity(false);
        logger.addAppender(this.appender);
        return  logger;
    }

    public void close() throws IOException {
        this.appender.close();
    }

    public static void main(String[] args) {
        LogUtils logUtils = new LogUtils("logs/ABC.log");
        logUtils.getLogger().info("Hi! Welcome");
        logUtils.getLogger().info("Hi! Welcome 1");
        show(logUtils);
    }

    public static void show(LogUtils logUtils){
        logUtils.getLogger().info("Hi! Welcome 2");
    }
}
