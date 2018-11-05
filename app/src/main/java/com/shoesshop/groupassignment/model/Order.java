package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;
import com.shoesshop.groupassignment.room.entity.ProductVariant;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private ArrayList<ProductVariant> orderDetailList;

    private int paymentType;

    private String note;

    private String accessToken;

    private String shippingAddress;

    public ArrayList<ProductVariant> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(ArrayList<ProductVariant> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
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
