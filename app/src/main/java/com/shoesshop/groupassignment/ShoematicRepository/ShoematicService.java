package com.shoesshop.groupassignment.ShoematicRepository;

import com.shoesshop.groupassignment.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShoematicService {

    @FormUrlEncoded
    @POST(ConfigApi.Api.LOGIN_BY_PHONE)
    Call<ResponseBody> loginByPhone(@Field("fbAccessToken") String fbAccessToken);

    @FormUrlEncoded
    @POST(ConfigApi.Api.LOGIN_FACEBOOK)
    Call<ResponseBody> loginByFacebook(@Field("access_token") String fbAccessToken);

    @GET(ConfigApi.Api.GET_PRODUCT)
    Call<ResponseBody> getProduct();

    @POST(ConfigApi.Api.SET_ORDER)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> setOrder(@Body RequestBody orderJsonObject);

    @POST(ConfigApi.Api.UPDATE_CUSTOMER)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> updateCustomer(@Body RequestBody customerJsonObject);
}
