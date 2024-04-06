package com.ocado.basket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            String basketFilePath = "src/main/resources/basket-1.json";
            List<String> basket = BasketLoader.loadBasket(basketFilePath);

            String configFilePath = "src/main/resources/config.json";
            BasketSplitter splitter = new BasketSplitter(configFilePath, basket);

            Map<String, List<String>> deliveryGroups = splitter.split();

            System.out.println("Delivery Groups:");
            for (Map.Entry<String, List<String>> entry : deliveryGroups.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}