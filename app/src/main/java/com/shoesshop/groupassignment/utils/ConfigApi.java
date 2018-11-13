package com.shoesshop.groupassignment.utils;

public class ConfigApi {
    public static final String BASE_URL = "http://172.16.10.74/api/";

    public interface Api {
        String LOGIN_FACEBOOK = "Customer/loginByFb";
        String LOGIN_BY_PHONE = "Customer/loginByPhone";
        String GET_PRODUCT = "Product/get";
        String SET_ORDER = "Order/create";
        String GET_ORDER_HISTORY = "Order/get";
        String UPDATE_CUSTOMER = "Customer/updateCus";

    }
}
