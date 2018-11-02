package com.shoesshop.groupassignment.room.Converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shoesshop.groupassignment.room.entity.ProductVariant;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter implements Serializable {
    @TypeConverter // note this annotation
    public String fromVariantList(List<ProductVariant> productVariantList) {
        if (productVariantList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductVariant>>() {
        }.getType();
        String json = gson.toJson(productVariantList, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<ProductVariant> toVariantList(String variantListString) {
        if (variantListString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductVariant>>() {
        }.getType();
        List<ProductVariant> productVariantList = gson.fromJson(variantListString, type);
        return productVariantList;
    }
}
