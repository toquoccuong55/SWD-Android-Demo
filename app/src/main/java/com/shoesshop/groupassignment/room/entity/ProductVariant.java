package com.shoesshop.groupassignment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "variant")
public class ProductVariant implements Serializable {
    @SerializedName("ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("UnitPrice")
    @ColumnInfo(name = "UnitPrice")
    private double unitPrice;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @SerializedName("Size")
    private int size;

    @ColumnInfo(name = "isSelected")
    private boolean isSelected;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
