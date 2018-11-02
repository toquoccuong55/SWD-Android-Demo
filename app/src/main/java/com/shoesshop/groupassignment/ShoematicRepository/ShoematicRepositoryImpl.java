package com.shoesshop.groupassignment.ShoematicRepository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shoesshop.groupassignment.model.Order;
import com.shoesshop.groupassignment.model.OrderItem;
import com.shoesshop.groupassignment.model.OrderResult;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.model.ResponseResult;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.utils.ClientApi;
import com.shoesshop.groupassignment.utils.KProgressHUDManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoematicRepositoryImpl implements ShoematicRepository {

    @Override
    public void loginByPhone(final Context context, String fbAccessToken, final CallBackData<Customer> callBackData) {
        ClientApi clientApi = new ClientApi();
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        Call<ResponseBody> serviceCall = clientApi.shoematicService().loginByPhone(fbAccessToken);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 || response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<Customer>>() {
                        }.getType();

                        ResponseResult<Customer> responseResult = new Gson().fromJson(result, type);

                        Customer customer = responseResult.getData();
                        if (customer == null) {
                            callBackData.onFail(response.message());
                        }
                        callBackData.onSuccess(customer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(context, khub);
                callBackData.onFail(t.getMessage());
                khub.dismiss();
            }
        });
    }

    @Override
    public void loginByFacebook(final Context context, String fbAccessToken, final CallBackData<Customer> callBackData) {
        ClientApi clientApi = new ClientApi();//call clienApi
        Call<ResponseBody> serviceCall = clientApi.shoematicService().loginByFacebook(fbAccessToken);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {

                        String result = response.body().string();

                        Type type = new TypeToken<ResponseResult<Customer>>() {
                        }.getType();

                        ResponseResult<Customer> responseResult =
                                new Gson().fromJson(result, type);

                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        }
                        Customer customer = responseResult.getData();
                        callBackData.onSuccess(customer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //  KProgressHUDManager.dismiss(context, khub);
                callBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getProductList(final Context context, final CallBackData<List<Product>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall = clientApi.shoematicService().getProduct();
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response != null && response.body() != null) {
                    if (response.code() == 200) {
                        try {
                            String result = response.body().string();

                            Type type = new TypeToken<ResponseResult<List<Product>>>() {
                            }.getType();

                            ResponseResult<List<Product>> responseResult = new Gson().fromJson(result, type);
                            if (responseResult.getData() == null) {
                                callBackData.onFail(response.message());
                            }
                            List<Product> productList = responseResult.getData();
                            callBackData.onSuccess(productList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callBackData.onFail(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(context, khub);
                callBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void setOrder(final Context context, Order order, String accessToken, final CallBackData<OrderResult> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject orderJsonObject = new JSONObject();
        try {
            JSONArray orderDetailJsonArray = new JSONArray();
            for (OrderItem orderItem : order.getOrderDetailList()) {
                JSONObject orderItemJsonObject = new JSONObject();
                orderItemJsonObject.put("ProductId", orderItem.getProductDetailId());
                orderItemJsonObject.put("Quantity", orderItem.getQuantity());
                orderDetailJsonArray.put(orderItemJsonObject);
            }
            orderJsonObject.put("OrderDetails", orderDetailJsonArray);
            orderJsonObject.put("PaymentType", order.getPaymentType());
            orderJsonObject.put("Notes", order.getNote());
            //    orderJsonObject.put("isDeliveryFree", false);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), orderJsonObject.toString());
        Call<ResponseBody> serviceCall = clientApi.shoematicService().setOrder(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<OrderResult>>() {

                        }.getType();
                        //call response to get value data
                        ResponseResult<OrderResult> responseResult = new Gson().fromJson(result, type);
                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        }
                        OrderResult orderResult = responseResult.getData();
                        callBackData.onSuccess(orderResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
