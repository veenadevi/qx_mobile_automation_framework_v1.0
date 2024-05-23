package com.qt.commonutils;


/**
 * This Class has all the Objects related to FileReaderManager.
 *
 * @author
 */
public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;

	/**
	 * Private constructor to prevent instantiation of the FileReaderManager class from outside.
	 */

	private FileReaderManager() {
	}

	/**
	 * Gets the singleton instance of FileReaderManager.
	 *
	 * @return the singleton instance of FileReaderManager
	 */
	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}


	/**
	 * Gets the ConfigFileReader instance.
	 *
	 * @return the ConfigFileReader instance
	 */
	public ConfigFileReader getConfigReader() {
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}


	
}
