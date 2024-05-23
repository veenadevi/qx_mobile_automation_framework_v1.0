package com.qt.bdd.runner;

import com.qt.config.Global;
import com.qt.model.Device;

import java.util.List;

/**
 * Sample class to demonstrate retrieving the list of devices from the configuration.
 */

public class Sample {

    /**
     * Main method to demonstrate functionality.
     * Retrieves the list of devices from the configuration and prints the size of the list.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        List<Device> devices = Global.getConfig().getDevices();
        System.out.println(devices.size());

    }
}
