package com.qt.commonutils;

import org.apache.commons.configuration.ConfigurationException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This Class has all the Objects related to PropUtils.
 *
 * @author
 */
public class PropUtils {

    private Map<String, Object> globalVariable = new HashMap<String, Object>();

    /**
     * Returns null if something went wrong*/
    public Properties getProperties(String filePath){
        return this.getProperties(new File(filePath));
    }

    /**
     * Loads properties from the specified file and returns them.
     *
     * @param file The file containing the properties.
     * @return The Properties object loaded from the file.
     */
    public Properties getProperties(File file){
        try (InputStream inputStream = new FileInputStream(file)){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds properties from the provided Properties object to the configuration file specified by the file path.
     *
     * @param properties The Properties object containing the properties to be added.
     * @param filePath   The path to the configuration file.
     * @throws ConfigurationException If an error occurs while adding properties to the file.
     */
    public void addProperties(Properties properties, String filePath) throws ConfigurationException {
        this.addProperties(properties, new File(filePath));
    }
    /**
     * Adds properties from the provided Properties object to the specified file.
     *
     * @param properties1 The Properties object containing the properties to be added.
     * @param file        The file to which properties will be added.
     * @throws ConfigurationException If an error occurs while adding properties to the file.
     */


    public void addProperties(Properties properties1, File file) throws ConfigurationException {
        Properties oldProp = this.getProperties(file);
        oldProp.putAll(properties1);
        try (OutputStream outputStream = new FileOutputStream(file)){
            oldProp.store(outputStream, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * settter and getter methods for key and value
     * @param key
     * @param value
     */
    public void setGlobalVariable(String key, Object value){
        this.globalVariable.put(key, value);
    }

    public Object getGlobalVariable(String key){
        return this.globalVariable.get(key);
    }

    /**
     * Retrieves the file extension from the given file name.
     *
     * @param fileName The name of the file.
     * @return The file extension.
     */
    public String getFileExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }



}
