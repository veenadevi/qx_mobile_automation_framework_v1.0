package com.qt.commonutils;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Manages the Appium related utilities testing mobile applications.
 * @author
 */
public class AppiumManagerUtils {
    /**
     * Get a free port number.
     *
     * @return A free port number.
     * @throws Exception If an error occurs while getting the free port.
     */

    public static int getFreePort() throws Exception {
        int port = 0;
        try {
            ServerSocket socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
            socket.close();
        } catch (Exception e) {
            System.err.println("Exception while generating free port.");
            e.printStackTrace();
        }
        return port;
    }


    /**
     * Get the path of the Node.js executable.
     *
     * @return The path of the Node.js executable.
     * @throws IOException  If an I/O error occurs.
     * @throws Exception    If an error occurs while getting the node path.
     */
    public static String getNodePath() throws IOException, Exception {
        String jsPaths = null;
        String nodePath = null;

        Process p;
        BufferedReader reader;
        String operatingSystem = System.getProperty("os.name");

        if (operatingSystem.contains("Win")) {
            String whereAppium = "where" + " " + "node";
            p = Runtime.getRuntime().exec(whereAppium);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((jsPaths = reader.readLine()) != null) {
                nodePath = jsPaths;
                break;
            }

            p.waitFor();
            p.destroy();

            if (nodePath == null) {
                System.exit(0);
            }
        } else {
            String command = "which " + "node";
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = reader.readLine()) != null) {
                nodePath = line;
                break;
            }

            p.destroy();
            if (nodePath == null) {
                System.exit(0);
            }
        }

        System.out.println("nodePath: " + nodePath);
        return nodePath;
    }

    /**
     * Get the path of the Appium JavaScript file (main.js).
     *
     * @return The path of the Appium JavaScript file.
     * @throws IOException  If an I/O error occurs.
     * @throws Exception    If an error occurs while getting the Appium JS path.
     */

    public static String getAppiumJSPath() throws IOException, Exception {

        String jsPaths = null;
        String actualJSPath = null;
        String operatingSystem = System.getProperty("os.name");

        if (operatingSystem.contains("Win")) {
            String whereAppium = "where" + " " + "appium";
            Process p = Runtime.getRuntime().exec(whereAppium);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((jsPaths = stdInput.readLine()) != null) {
                actualJSPath = jsPaths.replace("appium", "node_modules\\appium\\build\\lib\\main.js");
                break;
            }
            p.waitFor();
            p.destroy();

            if (actualJSPath == null) {
                System.exit(0);
            }
        } else {
            actualJSPath = "//usr//local//lib//node_modules//appium//build//lib//main.js";
        }

        System.out.println("AppiumJSPath: " + actualJSPath);
        return actualJSPath;
    }


    /**
     * Retrieves the JSON configuration from the specified file path.
     * If the file path is null, it defaults to the capabilities.json file in the 'caps' directory.
     *
     * @param jsonPath The file path of the JSON configuration file.
     * @return The JSON configuration object.
     */

    public JSONObject getConfigJSON(String jsonPath){
        if(jsonPath == null){
            jsonPath = System.getProperty("user.dir") + File.separator + "caps" + File.separator + "capabilities.json";
        }
        JsonParser jsonParser = new JsonParser(jsonPath);
        return jsonParser.getObjectFromJSON();
    }
    /**
     * Checks if the provided host is a local host.
     *
     * @param host The host to check.
     * @return True if the host is a local host, otherwise false.
     */


    public boolean isLocalHost(String host)  {
        try {
            if(host.equalsIgnoreCase("localhost") || host.equalsIgnoreCase("http://localhost") || host.equals("127.0.0.1") || host.equals("0.0.0.0")) {
                return true;
            }

            // Compare public IP address
            String requestHostAddress = InetAddress.getByName(host).getHostAddress();
            String myHostAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println("requestHostAddress: " + requestHostAddress);
            System.out.println("myHostAddress: " + myHostAddress);

            if(requestHostAddress.equals(myHostAddress)) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("--Exception while checking isMyLocalAdress");
        }

        return false;
    }

    /**
     * Retrieves the major version number from the provided version string.
     *
     * @param version The version string.
     * @return The major version number.
     */
    public static int getMajorVersion(String version){
        System.out.println("Debug ...." + version.indexOf("."));
        String major = version.substring(0, version.indexOf("."));
        return  Integer.parseInt(major);
    }
}
