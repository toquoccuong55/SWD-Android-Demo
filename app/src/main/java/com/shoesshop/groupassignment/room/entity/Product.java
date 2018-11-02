package com.shoesshop.groupassignment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.shoesshop.groupassignment.room.Converter.DataConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "product")
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int productId;

    @SerializedName("id")
    @ColumnInfo(name = "product_id")
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "product_name")
    private String name;

    @SerializedName("unit_price")
    @ColumnInfo(name = "unit_price")
    private double unitPrice;

    @SerializedName("picURL")
    @ColumnInfo(name = "picURL")
    private String image;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "isFavorite")
    private boolean isFavorite;

    @SerializedName("variant")
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "product_variant_list")
    private List<ProductVariant> productVariantList;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

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

    public Product(String image, String name, double unitPrice) {
        this.image = image;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

