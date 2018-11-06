package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SuccessedOrder implements Serializable {
    @SerializedName("order_id")
    private int orderId;

    @SerializedName("date")
    private CreatedDate createdDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CreatedDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(CreatedDate createdDate) {
        this.createdDate = createdDate;
    }
}
