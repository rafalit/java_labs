package com.ocado.basket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConfigLoader {
    public static Config loadConfig(String filePath) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {

            return gson.fromJson(reader, Config.class);

        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
