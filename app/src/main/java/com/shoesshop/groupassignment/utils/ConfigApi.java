package com.shoesshop.groupassignment.utils;

public class ConfigApi {
    public static final String BASE_URL = "http://35.240.250.249/api/";


    public interface Api {
        String LOGIN_FACEBOOK = "LoginByFacebook";
        String LOGIN_BY_PHONE = "LoginByPhoneNumber";
        String GET_PRODUCT = "Product/get";
        String SET_ORDER = "setOrder";
    }
}
