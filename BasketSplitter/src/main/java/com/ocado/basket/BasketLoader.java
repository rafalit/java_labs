package com.ocado.basket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class BasketLoader {

    private static final int MAX_PRODUCTS = 100;

    public static List<String> loadBasket(String filePath) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        try (FileReader reader = new FileReader(filePath)) {
            List<String> basket = gson.fromJson(reader, listType);
            if (basket.size() > MAX_PRODUCTS) {
                throw new IllegalArgumentException("Basket cannot contain more than " + MAX_PRODUCTS + " products.");
            }
            return basket;
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("Invalid JSON syntax in the basket file.", e);
        }
    }
}