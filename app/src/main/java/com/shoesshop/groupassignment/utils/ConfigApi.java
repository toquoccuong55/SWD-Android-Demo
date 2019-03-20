package com.shoesshop.groupassignment.utils;

public class ConfigApi {
    public static final String BASE_URL = "http://10.0.2.2:45456/api/";

    public interface Api {
        String LOGIN_FACEBOOK = "Customer/loginByFb";
        String LOGIN_BY_PHONE = "Customer/loginByPhone";
        String UPDATE_CUSTOMER = "Customer/updateCustomer";
        String GET_PRODUCT = "Product/getProducts";
        String SET_ORDER = "Order/setOrder";
        String GET_ORDER_HISTORY = "Order/OrderHistory";
    }
}
