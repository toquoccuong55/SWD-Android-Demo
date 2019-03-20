package com.shoesshop.groupassignment.model;

import com.google.gson.annotations.SerializedName;

public class ResponseResult<T> {
    private Status status;

    private T data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
