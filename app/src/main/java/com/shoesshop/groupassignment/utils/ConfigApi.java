package com.shoesshop.groupassignment.utils;

public class ConfigApi {
    public static final String BASE_URL = "https://192.168.1.122:45458/";

    public interface Api {
        String LOGIN_FACEBOOK = "Customer/loginByFb";
        String LOGIN_BY_PHONE = "Customer/loginByPhone";
        String UPDATE_CUSTOMER = "Customer/updateCus";
        String GET_PRODUCT = "Product/get";
        String SET_ORDER = "Order/create";
        String GET_ORDER_HISTORY = "Order/get";
    }
}
