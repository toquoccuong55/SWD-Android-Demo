package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderHistoryDetail implements Serializable {

    @SerializedName("ProductID")
    private int productId;

    @SerializedName("imageURL")
    private String orderDetailImage;

    @SerializedName("Name")
    private String orderDetailTitle;

    @SerializedName("Size")
    private String sizeName;

    @SerializedName("UnitPrice")
    private double unitPrice;

    @SerializedName("Quantity")
    private int quantity;

    public OrderHistoryDetail(String orderDetailImage, String orderDetailTitle, String sizeName, double unitPrice, int quantity) {
        this.orderDetailImage = orderDetailImage;
        this.orderDetailTitle = orderDetailTitle;
        this.sizeName = sizeName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOrderDetailImage() {
        return orderDetailImage;
    }

    public void setOrderDetailImage(String orderDetailImage) {
        this.orderDetailImage = orderDetailImage;
    }

    public String getOrderDetailTitle() {
        return orderDetailTitle;
    }

    public void setOrderDetailTitle(String orderDetailTitle) {
        this.orderDetailTitle = orderDetailTitle;
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
