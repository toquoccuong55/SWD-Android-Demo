package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateCustomerResult implements Serializable {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
