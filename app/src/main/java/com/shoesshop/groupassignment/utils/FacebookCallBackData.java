package com.shoesshop.groupassignment.utils;

public interface FacebookCallBackData {
    void onSuccess(boolean isLogged);

    void onFail(String message);
}
