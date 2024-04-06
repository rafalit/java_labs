package com.ocado.basket;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    @Test
    void testLoadConfigValidFile() throws IOException {
        String filePath = "src/main/resources/config.json";
        Config config = ConfigLoader.loadConfig(filePath);
        assertNotNull(config);
    }

    @Test
    void testLoadConfigInvalidFileFormat() {
        String filePath = "src/main/resources/basket-1.json";
        assertThrows(IllegalArgumentException.class, () -> {
            ConfigLoader.loadConfig(filePath);
        });
    }

    @Test
    void testLoadConfigNonexistentFile() {
        String filePath = "nonexistent_config.json";
        assertThrows(FileNotFoundException.class, () -> {
            ConfigLoader.loadConfig(filePath);
        });
    }


}