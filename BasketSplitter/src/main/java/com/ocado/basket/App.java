package com.ocado.basket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            Map<String, String> basketFiles = new HashMap<>();
            basketFiles.put("Basket 1", "src/main/resources/basket-1.json");
            basketFiles.put("Basket 2", "src/main/resources/basket-2.json");
            basketFiles.put("Basket 3", "src/main/resources/basket-3.json");
            basketFiles.put("Basket 4", "src/main/resources/basket-4.json");
            basketFiles.put("Basket 5", "src/main/resources/basket-5.json");

            for (Map.Entry<String, String> entry : basketFiles.entrySet()) {
                String basketName = entry.getKey();
                String basketFilePath = entry.getValue();

                List<String> basket = BasketLoader.loadBasket(basketFilePath);

                String configFilePath = "src/main/resources/config.json";
                BasketSplitter splitter = new BasketSplitter(configFilePath, basket);

                Map<String, List<String>> deliveryGroups = splitter.split();

                System.out.println("Delivery Groups for " + basketName + ":");
                for (Map.Entry<String, List<String>> group : deliveryGroups.entrySet()) {
                    System.out.println(group.getKey() + ": " + group.getValue());
                }
                System.out.println();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
