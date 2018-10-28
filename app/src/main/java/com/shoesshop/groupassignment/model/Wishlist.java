package com.shoesshop.groupassignment.model;

public class Wishlist {
    private String productImage;
    private String productName;
    private double unitPrice;

    public Wishlist(String productImage, String productName, double unitPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
