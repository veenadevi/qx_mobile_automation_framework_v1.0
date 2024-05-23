package com.qt.commonutils;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This Class has all the Objects related to JsonParser.
 *
 * @author
 */
public class JsonParser {

    private String filePath;

    public JsonParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads JSON content from a file, parses it into a JSONArray, and returns it.
     *
     * @return The JSONArray parsed from the JSON content of the file.
     */

    public JSONArray getJsonParsedObjectAsJsonArray() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
            String jsonContent = IOUtils.toString(bufferedReader);
            return new JSONArray(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Reads JSON content from a file specified by filePath,
     * parses it into a JSONObject, and returns it.
     *
     * @return The JSONObject parsed from the JSON content.
     * @throws IOException If an I/O error occurs while reading the file.
     */

    public JSONObject getObjectFromJSON() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
            String jsonContent = IOUtils.toString(bufferedReader);
            return new JSONObject(jsonContent);
        } catch (IOException e) {
            System.err.println("Exception while jsonParser");
            e.printStackTrace();
        }
        return null;
    }
}
