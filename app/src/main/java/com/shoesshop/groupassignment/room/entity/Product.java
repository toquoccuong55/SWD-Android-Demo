package com.shoesshop.groupassignment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.shoesshop.groupassignment.room.Converter.DataConverter;
import com.shoesshop.groupassignment.room.Converter.ImageListConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "product")
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int productIDInfo;

    @SerializedName("ID")
    @ColumnInfo(name = "product_id")
    private int id;

    @SerializedName("Name")
    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "unit_price")
    private double unitPrice;

    @SerializedName("imageList")
    @TypeConverters(ImageListConverter.class)
    @ColumnInfo(name = "imageList")
    private List<String> imageList;

    @SerializedName("Description")
    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "isFavorite")
    private boolean isFavorite;

    @SerializedName("ProductSkus")
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "variant")
    private List<ProductVariant> productVariantList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductVariant> getProductVariantList() {
        return productVariantList;
    }

    public void setProductVariantList(List<ProductVariant> productVariantList) {
        this.productVariantList = productVariantList;
    }

    public Product() {
    }

    public int getProductIDInfo() {
        return productIDInfo;
    }

    public void setProductIDInfo(int productIDInfo) {
        this.productIDInfo = productIDInfo;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}

