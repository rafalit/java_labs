package com.ocado.basket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.List;

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
            throw new FileNotFoundException("Config file not found!");
        }
    }

    public static void validateConfig(Config config) {
        if (config.getDeliveryOptions().size() > 1000) {
            throw new IllegalArgumentException("Config cannot contain more than 1000 products.");
        }

        for (List<String> deliveryOptions : config.getDeliveryOptions().values()) {
            if (deliveryOptions.size() > 10) {
                throw new IllegalArgumentException("Each product cannot have more than 10 delivery options.");
            }
        }
    }
}
