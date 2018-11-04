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

    @SerializedName("access_token")
    @ColumnInfo(name = "accessToken")
    private String accessToken;

    @SerializedName("name")
    @ColumnInfo(name = "fullName")
    private String fullName;

    @SerializedName("address")
    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "address_type")
    private String addressType;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private String phone;

    @SerializedName("first_login")
    @ColumnInfo(name = "isFirstLogin")
    private int isFirstLogin;

    @SerializedName("phone_login")
    @ColumnInfo(name = "phone_login")
    private boolean isPhoneLogin;

    @ColumnInfo(name = "email")
    private String email;

    public boolean isPhoneLogin() {
        return isPhoneLogin;
    }

    public void setPhoneLogin(boolean phoneLogin) {
        isPhoneLogin = phoneLogin;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public int getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(int isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
