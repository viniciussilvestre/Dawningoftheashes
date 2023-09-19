package com.example.dawningoftheashes.util;

import androidx.room.TypeConverter;

import com.example.dawningoftheashes.model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.dawningoftheashes.model.Monster;
import com.example.dawningoftheashes.model.Sheet;

public class Converters
{
    @TypeConverter
    public static ArrayList<Sheet> fromSheetString(String value)
    {
        Type listType = new TypeToken<ArrayList<Sheet>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromSheetArrayList(ArrayList<Sheet> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Monster> fromMonsterString(String value)
    {
        Type listType = new TypeToken<ArrayList<Monster>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromMonsterArrayList(ArrayList<Monster> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Player> fromPlayerString(String value)
    {
        Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromPlayerArrayList(ArrayList<Player> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
