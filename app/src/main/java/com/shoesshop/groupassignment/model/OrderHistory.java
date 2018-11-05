package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderHistory implements Serializable {
    @SerializedName("id")
    private String orderID;

    @SerializedName("date")
    private String OrderTime;

    @SerializedName("shippingaddress")
    private String shippingaddress;


    private double orderTotal;
    private String orderDetailImage;
    private String orderDetailName;
    private String unitPriceQuantity;

    @SerializedName("paymenttype")
    private int paymentType;

    @SerializedName("paymentamount")
    private double paymentAmount;

    @SerializedName("status")
    private int orderStatus;

    @SerializedName("order_detail")
    private List<OrderHistoryDetail> detailHistoryList;

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
