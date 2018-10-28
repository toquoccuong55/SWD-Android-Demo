package com.shoesshop.groupassignment.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String image;
    private String name;
    private double unitPrice;


    public Product(String image, String name, double unitPrice) {
        this.image = image;
        this.name = name;
        this.unitPrice = unitPrice;
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

