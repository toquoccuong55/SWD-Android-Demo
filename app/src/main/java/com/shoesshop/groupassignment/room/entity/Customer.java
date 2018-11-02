package com.shoesshop.groupassignment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "customers")
public class Customer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int customerId;

    @SerializedName("customer_id")
    @ColumnInfo(name = "customerInfoID")
    private String customerInfoID;

    @SerializedName("access_token")
    @ColumnInfo(name = "accessToken")
    private String accessToken;

    @SerializedName("name")
    @ColumnInfo(name = "fullName")
    private String fullName;

    @SerializedName("pic_url")
    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private String phone;

    @SerializedName("is_first_login")
    @ColumnInfo(name = "isFirstLogin")
    private boolean isFirstLogin;

    @ColumnInfo(name = "email")
    private String email;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerInfoID() {
        return customerInfoID;
    }

    public void setCustomerInfoID(String customerInfoID) {
        this.customerInfoID = customerInfoID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
