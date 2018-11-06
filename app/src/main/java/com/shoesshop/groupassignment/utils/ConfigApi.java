package com.shoesshop.groupassignment.utils;

public class ConfigApi {
    //    public static final String BASE_URL = "https://35.240.250.249/api/";
//    public static final String BASE_URL = "http://172.16.4.205/api/";
    public static final String BASE_URL = "http://192.168.1.4/api/";

    public interface Api {
        String LOGIN_FACEBOOK = "Customer/loginByFb";
        String LOGIN_BY_PHONE = "Customer/loginByPhone";
        String GET_PRODUCT = "Product/get";
        String SET_ORDER = "Order/create";
        String GET_ORDER_HISTORY = "Order/get";
        String UPDATE_CUSTOMER = "Customer/updateCus";

    }
}
