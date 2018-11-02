package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderResult implements Serializable {
    @SerializedName("invoice_id")
    private String invoice_id;
    @SerializedName("order_status")
    private int order_status;

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
}
