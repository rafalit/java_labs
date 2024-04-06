package com.ocado.basket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ConfigLoader {
    public static Config loadConfig(String filePath) throws IOException {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            if (gson.fromJson(reader, Object.class) instanceof Map) {
                reader.close();
                return gson.fromJson(new FileReader(filePath), Config.class);
            } else {
                throw new IllegalArgumentException("Invalid JSON format: Array is not supported.");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("There is no filed like this!");
        }
    }
}
