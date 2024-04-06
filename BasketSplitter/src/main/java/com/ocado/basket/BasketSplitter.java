package com.ocado.basket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BasketSplitter {
    private Config config;
    private List<String> basket;

    public BasketSplitter(String absolutePathToConfigFile, List<String> basket) throws IOException {
        try {
            this.config = ConfigLoader.loadConfig(absolutePathToConfigFile);
        } catch (FileNotFoundException e) {
            throw new IOException("Config file not found!", e);
        }
        this.basket = basket;
    }
    public Map<String, List<String>> split() {
        if (basket == null) {
            throw new IllegalStateException("Basket is null. Please provide a valid basket.");
        }

        Map<String, List<String>> deliveryGroups = new HashMap<>();

        while (!basket.isEmpty()) {
            Map<String, List<String>> deliveryOptionsForProducts = new HashMap<>();

            for (String product : basket) {
                if(!config.getDeliveryOptions().containsKey(product)) {
                    throw new IllegalArgumentException("Product '" + product + "' does not exist in the delivery configuration.");
                }

                try {
                    // Getting available delivery options for the current product from the config
                    List<String> availableDeliveryOptions = config.getDeliveryOptions().getOrDefault(product, Collections.emptyList());

                    // Adding the product and its available delivery options to the map
                    for (String deliveryOption : availableDeliveryOptions) {
                        deliveryOptionsForProducts.computeIfAbsent(deliveryOption, k -> new ArrayList<>()).add(product);
                    }
                } catch(Exception e) {
                    throw new RuntimeException("An error occurred while processing product '" + product + "'", e);
                }
            }

            // Finding the delivery option with the most products
            String largestDeliveryOption = "";
            int largestSize = 0;
            for (Map.Entry<String, List<String>> entry : deliveryOptionsForProducts.entrySet()) {
                if (entry.getValue().size() > largestSize) {
                    largestSize = entry.getValue().size();
                    largestDeliveryOption = entry.getKey();
                }
            }

            // Adding the largest delivery option to the result
            if (!largestDeliveryOption.isEmpty()) {
                deliveryGroups.put(largestDeliveryOption, deliveryOptionsForProducts.get(largestDeliveryOption));
                // Removing products assigned to the largest delivery option from the basket
                basket.removeAll(deliveryOptionsForProducts.get(largestDeliveryOption));
            } else {
                // No suitable delivery option found for remaining products
                break;
            }
        }

        return deliveryGroups;
    }
}


