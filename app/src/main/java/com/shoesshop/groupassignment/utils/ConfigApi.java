package com.shoesshop.groupassignment.utils;

public class ConfigApi {
    public static final String BASE_URL = "https://35.240.250.249/api/";
//    public static final String BASE_URL = "http://172.16.2.146/android/public/api/";


    public interface Api {
        String LOGIN_FACEBOOK = "Customer/loginByFb";
        String LOGIN_BY_PHONE = "LoginByPhoneNumber";
        String GET_PRODUCT = "Product/get";
        String SET_ORDER = "setOrder";
        String UPDATE_CUSTOMER = "Customer/updateCus";

    }
}
