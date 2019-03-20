package com.shoesshop.groupassignment.room.Converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class ImageListConverter implements Serializable {

    @TypeConverter
    public String fromImageList(List<String> imageList) {
        if (imageList == null) {
            return "";
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(imageList, type);

        return json;
    }

    @TypeConverter
    public List<String> toImageList(String imageListStr) {
        if (imageListStr == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> imageList = gson.fromJson(imageListStr, type);

        return imageList;
    }
}
