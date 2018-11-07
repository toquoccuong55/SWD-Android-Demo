package com.shoesshop.groupassignment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.shoesshop.groupassignment.model.Size;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "variant")
public class ProductVariant implements Serializable {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("sku")
    @ColumnInfo(name = "sku")
    private String name;

    @SerializedName("price")
    @ColumnInfo(name = "price")
    private double unitPrice;

    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    private int quantity;

    @SerializedName("Color")
    @ColumnInfo(name = "Color")
    private String color;

    @SerializedName("Size")
    @Ignore
    private String sizeString;

    @Ignore
    private Size size;

    @SerializedName("img")
    @Ignore
    private List<String> picURLList;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "buyQuantity")
    private int buyQuantity;

    @ColumnInfo(name = "isSelected")
    private boolean isSelected;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSizeString() {
        return sizeString;
    }

    public void setSizeString(String sizeString) {
        this.sizeString = sizeString;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

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

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
