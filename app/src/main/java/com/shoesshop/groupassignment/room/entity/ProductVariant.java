package com.shoesshop.groupassignment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;
import com.shoesshop.groupassignment.model.Size;

import java.util.List;

@Entity(tableName = "variant")
public class ProductVariant {
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("proname")
    @ColumnInfo(name = "proname")
    private String name;

    @SerializedName("price")
    @ColumnInfo(name = "price")
    private double unitPrice;

    @SerializedName("Color")
    @ColumnInfo(name = "Color")
    private String color;

    @SerializedName("Size")
    @ColumnInfo(name = "Size")
    private Size size;

    @SerializedName("img")
    @ColumnInfo(name = "img")
    private List<String> picURLList;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "quantity")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getPicURLList() {
        return picURLList;
    }

    public void setPicURLList(List<String> picURLList) {
        this.picURLList = picURLList;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
