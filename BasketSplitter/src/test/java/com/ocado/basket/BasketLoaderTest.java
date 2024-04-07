package com.ocado.basket;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketLoaderTest {

    @Test
    void loadValidBasket() {
        String validFilePath = "src/main/resources/basket-1.json";

        assertDoesNotThrow(() -> {
            List<String> validBasket = BasketLoader.loadBasket(validFilePath);
            assertNotNull(validBasket);
            assertFalse(validBasket.isEmpty());
        });
    }

    @Test
    void loadInvalidFilePath() {
        String invalidFilePath = "non_existing_file.json";

        assertThrows(IOException.class, () -> {
            BasketLoader.loadBasket(invalidFilePath);
        });
    }

    @Test
    void loadLargeFile() {
        // Check if overpacked basket throws an IllegalArgumentException
        String largeFilePath = "src/main/resources/overpacked_basket.json";

        assertThrows(IllegalArgumentException.class, () -> {
            BasketLoader.loadBasket(largeFilePath);
        });
    }

    @Test
    void loadNullFilePath() {
        assertThrows(NullPointerException.class, () -> {
            BasketLoader.loadBasket(null);
        });
    }

    @Test
    void loadInvalidFileContent() {
        String invalidFile = "src/main/resources/config.json";

        assertThrows(JsonParseException.class, () -> {
            BasketLoader.loadBasket(invalidFile);
        });
    }
}
