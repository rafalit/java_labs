package com.ocado.basket;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketLoaderTest {

    @Test
    void loadBasket() {

        String validFilePath = "src/main/resources/basket-1.json";
        String invalidFilePath = "non_existing_file.json";
        String invalidFile = "src/main/resources/config.json";


        assertDoesNotThrow(() -> {
            List<String> validBasket = BasketLoader.loadBasket(validFilePath);
            assertNotNull(validBasket);
            assertFalse(validBasket.isEmpty());
        });

        assertThrows(IOException.class, () -> {
            BasketLoader.loadBasket(invalidFilePath);
        });

        assertThrows(JsonParseException.class, () -> {
            BasketLoader.loadBasket(invalidFile);
        });
    }
}
