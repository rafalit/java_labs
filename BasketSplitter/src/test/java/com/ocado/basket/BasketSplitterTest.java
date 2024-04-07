package com.ocado.basket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {

    @Test
    void split_EmptyBasket() throws IOException {
        // Check whether the basket is empty
        String configFilePath = "src/main/resources/config.json";
        List<String> emptyBasket = Arrays.asList();
        BasketSplitter splitter = new BasketSplitter(configFilePath, emptyBasket);
        Map<String, List<String>> deliveryGroups = splitter.split();

        assertTrue(deliveryGroups.isEmpty());
    }

    @Test
    void split_InvalidConfig() throws IOException {
        // Check whether the invalid configuration file triggers an IOException
        String configFilePath = "src/main/resources/config1.json";
        String validFilePath = "src/main/resources/basket-1.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        assertThrows(IOException.class, () -> new BasketSplitter(configFilePath, basket));
    }
    @Test
    void split_NullBasket() throws IOException {
        // Check whether null basket could be split
        String configFilePath = "src/main/resources/config.json";
        BasketSplitter splitter = new BasketSplitter(configFilePath, null);
        assertThrows(IllegalStateException.class, () -> splitter.split());
    }

    @Test
    void split_IsBasketNullAtTheEnd() throws IOException {
        // Check whether basket is null at the end
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/basket-1.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);
        Map<String, List<String>> deliveryGroups = splitter.split();
        assertEquals(basket.size(), 0);
    }

    @Test
    void split_DeliveryGroups1() throws IOException
    {
        // Check the delivery groups for Basket 1
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/basket-1.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);
        Map<String, List<String>> deliveryGroups = splitter.split();

        assertFalse(deliveryGroups.isEmpty());
        assertTrue(deliveryGroups.containsKey("Pick-up point"));
        assertFalse(deliveryGroups.containsKey("Next day shipping"));
        assertEquals(Arrays.asList("Cocoa Butter",
                                    "Tart - Raisin And Pecan",
                                    "Table Cloth 54x72 White",
                                    "Flower - Daisies",
                                    "Cookies - Englishbay Wht"), deliveryGroups.get("Courier"));

    }

    @Test
    void split_DeliveryGroups2() throws IOException
    {
        // Check whether all products from the basket are included in delivery groups
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/basket-2.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);
        Map<String, List<String>> deliveryGroups = splitter.split();

        Set<String> allProducts = new HashSet<>(basket);
        Collection<List<String>> allDeliveryGroups = deliveryGroups.values();
        List<String> allProductsInDeliveryGroups = new ArrayList<>();
        for (List<String> products : allDeliveryGroups) {
            allProductsInDeliveryGroups.addAll(products);
        }
        assertTrue(allProductsInDeliveryGroups.containsAll(allProducts));
    }

    @Test
    void split_DeliveryGroups3() throws IOException
    {
        // Check whether all delivery options specified in the config are included in delivery groups
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/basket-3.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);
        Map<String, List<String>> deliveryGroups = splitter.split();

        Set<String> deliveryOptionsFromConfig = new HashSet<>();
        Config config = ConfigLoader.loadConfig(configFilePath);
        for (List<String> options : config.getDeliveryOptions().values()) {
            deliveryOptionsFromConfig.addAll(options);
        }

        Set<String> deliveryOptionsFromGroups = new HashSet<>();
        for (List<String> options : deliveryGroups.values()) {
            deliveryOptionsFromGroups.addAll(options);
        }

        assertFalse(deliveryOptionsFromGroups.containsAll(deliveryOptionsFromConfig));
    }
    @Test
    void split_DeliveryGroups4() throws IOException {
        // Check delivery groups for Basket 4
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/basket-4.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);
        Map<String, List<String>> deliveryGroups = splitter.split();

        assertFalse(deliveryGroups.isEmpty());

        assertEquals(2, deliveryGroups.get("Pick-up point").size());
        assertEquals(1, deliveryGroups.get("Parcel locker").size());
        assertEquals(15, deliveryGroups.get("In-store pick-up").size());
        assertNull(deliveryGroups.get("Courier"));
    }

    @Test
    void split_DeliveryGroups5() throws IOException
    {
        // Check whether each product in Basket 5 has its own delivery group
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/basket-5.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);
        int basket_size=basket.size();

        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);
        Map<String, List<String>> deliveryGroups = splitter.split();

        assertEquals(deliveryGroups.size(), basket_size);
    }

    @Test
    void split_Exception1() throws IOException {
        // Check if invalid config throws an IllegalArgumentException
        String configFilePath = "src/main/resources/config2.json";
        String validFilePath = "src/main/resources/basket-1.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        assertThrows(IllegalArgumentException.class, () -> new BasketSplitter(configFilePath, basket));
    }

    @Test
    void split_Exception2() throws IOException {
        // Check if error in basket throws an IllegalArgumentException
        String configFilePath = "src/main/resources/config.json";
        String validFilePath = "src/main/resources/error_basket.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);
        BasketSplitter splitter = new BasketSplitter(configFilePath, basket);

        assertThrows(IllegalArgumentException.class, () -> splitter.split());
    }

    @Test
    void split_Exception3() throws IOException {
        // Check if error in basket throws an IllegalArgumentException
        String configFilePath = "src/main/resources/config3.json";
        String validFilePath = "src/main/resources/error_basket.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        assertThrows(IllegalArgumentException.class, () -> new BasketSplitter(configFilePath, basket));
    }

    @Test
    void split_Exception4() throws IOException {
        // Check if IllegalArgumentException is thrown for an invalid config
        String configFilePath = "src/main/resources/config3.json";
        String validFilePath = "src/main/resources/basket-1.json";
        List<String> basket = BasketLoader.loadBasket(validFilePath);

        assertThrows(IllegalArgumentException.class, () -> new BasketSplitter(configFilePath, basket));
    }
}