package com.shoesshop.groupassignment.model;

public class OrderHistoryDetail {
    private String orderDetailImage;
    private String orderDetailTitle;
    private String sizeName;
    private double unitPrice;
    private int quantity;

    public OrderHistoryDetail(String orderDetailImage, String orderDetailTitle, String sizeName, double unitPrice, int quantity) {
        this.orderDetailImage = orderDetailImage;
        this.orderDetailTitle = orderDetailTitle;
        this.sizeName = sizeName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
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
