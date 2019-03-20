package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderHistory implements Serializable {
    @SerializedName("OrderId")
    private int orderID;

    @SerializedName("OrderTime")
    private String OrderTime;

    @SerializedName("ShippingAddress")
    private String shippingaddress;


    private double orderTotal;
    private String orderDetailImage;
    private String orderDetailName;
    private String unitPriceQuantity;

    @SerializedName("PaymentType")
    private String paymentType;

    @SerializedName("TotalAmount")
    private double paymentAmount;

    @SerializedName("Status")
    private int orderStatus;

    @SerializedName("OrderDetailList")
    private List<OrderHistoryDetail> detailHistoryList;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public List<OrderHistoryDetail> getDetailHistoryList() {
        return detailHistoryList;
    }

    public void setDetailHistoryList(List<OrderHistoryDetail> detailHistoryList) {
        this.detailHistoryList = detailHistoryList;
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
