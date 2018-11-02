package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    @SerializedName("OrderDetails")
    private ArrayList<OrderItem> orderDetailList;

    @SerializedName("PaymentType")
    private int paymentType;

    @SerializedName("Notes")
    private String note;

    @SerializedName("access_token")
    private String accessToken;

    public ArrayList<OrderItem> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(ArrayList<OrderItem> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
