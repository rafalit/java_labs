package com.ocado.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {
    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
    }

    @Test
    void getDeliveryOptions_EmptyMap() {
        Map<String, List<String>> deliveryOptions = config.getDeliveryOptions();
        assertTrue(deliveryOptions.isEmpty());
    }

    @Test
    void getDeliveryOptions_ForExistingProduct() {
        config = new Config();
        config.getDeliveryOptions().put("Pepper - Red, Finger Hot", Arrays.asList("Next day shipping", "Same day delivery"));

        List<String> options = config.getDeliveryOptions().get("Pepper - Red, Finger Hot");

        assertNotNull(options);
        assertFalse(options.isEmpty());
        assertTrue(options.contains("Next day shipping"));
        assertTrue(options.contains("Same day delivery"));
    }

    @Test
    void getDeliveryOptions_ForNonExistingProduct()
    {
        List<String> options = config.getDeliveryOptions().get("nonExistingProduct");
        assertNull(options);
    }

    @Test
    void changeDeliveryOptions()
    {
        config.getDeliveryOptions().put("Pepper - Julienne, Frozen", Arrays.asList("In-store pick-up", "Next day shipping"));
        config.getDeliveryOptions().put("Cheese - Sheep Milk", Collections.singletonList("Mailbox delivery"));

        assertEquals(2, config.getDeliveryOptions().size());
        assertTrue(config.getDeliveryOptions().containsKey("Pepper - Julienne, Frozen"));
        assertEquals(1, config.getDeliveryOptions().get("Cheese - Sheep Milk").size());
        assertTrue(config.getDeliveryOptions().get("Cheese - Sheep Milk").contains("Mailbox delivery"));
    }

}
