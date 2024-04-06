package com.ocado.basket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
public class BasketLoader
{
    public static List<String> loadBasket(String filePath) throws IOException
    {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        try(FileReader reader = new FileReader(filePath))
        {
            return gson.fromJson(reader, listType);
        }
    }
}
