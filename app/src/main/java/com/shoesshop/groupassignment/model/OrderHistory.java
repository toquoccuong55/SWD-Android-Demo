package com.shoesshop.groupassignment.model;

public class OrderHistory {
    private String orderID;
    private String OrderTime;
    private double orderTotal;
    private String orderDetailImage;
    private String orderDetailName;
    private String unitPriceQuantity;
    private String orderStatus;

    public OrderHistory(String orderID, String orderTime, double orderTotal, String orderDetailImage, String orderDetailName, String unitPriceQuantity, String orderStatus) {
        this.orderID = orderID;
        OrderTime = orderTime;
        this.orderTotal = orderTotal;
        this.orderDetailImage = orderDetailImage;
        this.orderDetailName = orderDetailName;
        this.unitPriceQuantity = unitPriceQuantity;
        this.orderStatus = orderStatus;
    }

    public String getUnitPriceQuantity() {
        return unitPriceQuantity;
    }

    public void setUnitPriceQuantity(String unitPriceQuantity) {
        this.unitPriceQuantity = unitPriceQuantity;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }


    public String getOrderDetailImage() {
        return orderDetailImage;
    }

    public void setOrderDetailImage(String orderDetailImage) {
        this.orderDetailImage = orderDetailImage;
    }

    public String getOrderDetailName() {
        return orderDetailName;
    }

    public void setOrderDetailName(String orderDetailName) {
        this.orderDetailName = orderDetailName;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
