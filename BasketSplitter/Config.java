package com.ocado.basket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    private Map<String, List<String>> deliveryOptions;

    public Config() {
        this.deliveryOptions = new HashMap<>();
    }

    public Map<String, List<String>> getDeliveryOptions() {
        return deliveryOptions;
    }
}
