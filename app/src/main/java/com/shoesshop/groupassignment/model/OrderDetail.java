package com.shoesshop.groupassignment.model;

public class OrderDetail {
    private int id;
    private String name;
    private String picUrl;
    private String sizeName;
    private double unitPrice;
    private int quantity;

    public OrderDetail(int id, String name, String picUrl, String sizeName, double unitPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.picUrl = picUrl;
        this.sizeName = sizeName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
