package com.shoesshop.groupassignment.ShoematicRepository;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shoesshop.groupassignment.model.Order;
import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.model.SuccessedOrder;
import com.shoesshop.groupassignment.model.UpdateCustomerResult;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.model.ResponseResult;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
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

        JSONObject loginModel = new JSONObject();
        try {
            loginModel.put("accessToken", fbAccessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), loginModel.toString());
        Call<ResponseBody> serviceCall = clientApi.shoematicService().loginByPhone(body);
        Log.d("URL = ", clientApi.shoematicService().loginByPhone(body).request().toString());
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 || response.body() != null) {
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
                KProgressHUDManager.dismiss(context, khub);
                callBackData.onFail(t.getMessage());
                khub.dismiss();
            }
        });
    }

    @Override
    public void getProductList(final Context context, final CallBackData<List<Product>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall = clientApi.shoematicService().getProduct();
        Log.e("URL=", clientApi.shoematicService().getProduct().request().url().toString());
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
                        } catch (JsonSyntaxException jsonError) {
                            jsonError.printStackTrace();
                        }
                        callBackData.onFail(response.message());
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
    public void getOrderHistory(final Context context, String accessToken, final CallBackData<List<OrderHistory>> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject orderJsonObject = new JSONObject();
        try {
            orderJsonObject.put("accessToken", accessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), orderJsonObject.toString());
        Call<ResponseBody> serviceCall = clientApi.shoematicService().getOrderHistory(body);
        Log.e("URL=", clientApi.shoematicService().getOrderHistory(body).request().url().toString());
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response != null && response.body() != null) {
                    if (response.code() == 200) {
                        try {
                            String result = response.body().string();

                            Type type = new TypeToken<ResponseResult<List<OrderHistory>>>() {
                            }.getType();

                            ResponseResult<List<OrderHistory>> responseResult = new Gson().fromJson(result, type);
                            if (responseResult.getData() == null) {
                                callBackData.onFail(response.message());
                            }
                            List<OrderHistory> orderHistoryList = responseResult.getData();
                            callBackData.onSuccess(orderHistoryList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JsonSyntaxException jsonError) {
                            jsonError.printStackTrace();
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
    public void setOrder(final Context context, Order order, final CallBackData<SuccessedOrder> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject orderJsonObject = new JSONObject();
        try {
            orderJsonObject.put("AccessToken", order.getAccessToken());
            orderJsonObject.put("ShippingAddress", order.getShippingAddress());
            orderJsonObject.put("PaymentType", order.getPaymentType() == 1 ? "CASH" : "Other");
            orderJsonObject.put("Note", order.getNote());

            JSONArray orderDetailJsonArray = new JSONArray();
            for (ProductVariant variant : order.getOrderDetailList()) {
                JSONObject orderItemJsonObject = new JSONObject();
                orderItemJsonObject.put("ProductID", variant.getId());
                orderItemJsonObject.put("Quantity", variant.getQuantity());
                orderItemJsonObject.put("UnitPrice", variant.getUnitPrice());
                orderDetailJsonArray.put(orderItemJsonObject);
            }
            orderJsonObject.put("OrderDetailList", orderDetailJsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), orderJsonObject.toString());
        Call<ResponseBody> serviceCall = clientApi.shoematicService().setOrder(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        Log.d("", "URL: " + clientApi.shoematicService().setOrder(body).request().toString());
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<SuccessedOrder>>() {
                        }.getType();

                        ResponseResult<SuccessedOrder> responseResult = new Gson().fromJson(result, type);
                        if(responseResult.getStatus().isSuccess() == true){
                            callBackData.onFail(response.message());
                        }
                        SuccessedOrder successedOrder = responseResult.getData();
                        callBackData.onSuccess(successedOrder);
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

    @Override
    public void updateCustomer(final Context context, Customer customer, final CallBackData<Customer> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject customerJsonObject = new JSONObject();
        try {
            customerJsonObject.put("access_token", customer.getAccessToken());
            customerJsonObject.put("name", customer.getFullName());
            customerJsonObject.put("phone", customer.getPhone());
            customerJsonObject.put("email", customer.getEmail());
            customerJsonObject.put("address", customer.getAddress());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), customerJsonObject.toString());
        Call<ResponseBody> serviceCall = clientApi.shoematicService().updateCustomer(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        Log.d("", "URL = : " + clientApi.shoematicService().updateCustomer(body).request().toString());
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<Customer>>() {
                        }.getType();

                        ResponseResult<Customer> responseResult = new Gson().fromJson(result, type);
                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        }
                        Customer customer = responseResult.getData();
                        callBackData.onSuccess(customer);
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
