package com.shoesshop.groupassignment.utils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;

import java.util.Arrays;

public class FacebookHelper {

    public static void handleAccountKitLogin(AppCompatActivity context, String accessToken, final FacebookCallBackData callBackData) {
        if (!accessToken.isEmpty() || accessToken != null || !accessToken.equals("")) {
            Log.e("AccessToken", accessToken);
            callBackData.onSuccess(true);
        } else {
            callBackData.onFail("");
        }
    }

    public static void loginFacebook(AppCompatActivity context) {
        LoginManager.getInstance().logInWithReadPermissions((Activity) context, Arrays.asList("public_profile, email"));
    }

    public static void handleFacebookLogin(final AppCompatActivity context, CallbackManager callbackManager, final FacebookCallBackData callBackData) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult.getAccessToken() != null) {
                    String accessToken = loginResult.getAccessToken().getToken();
                    Log.e("Accesstoken", accessToken);
                    if (isFbLoggedIn()) {
                        callBackData.onSuccess(true);
                    }
                    //get phone number

                }
            }

            @Override
            public void onCancel() {
                Log.i("", "LoginManager FacebookCallback onCancel");
                if (isFbLoggedIn()) {
                    LoginManager.getInstance().logOut();
                    callBackData.onFail("");
                }
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("", "LoginManager FacebookCallback onError");

            }
        });
    }

    public static boolean isFbLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public static String getFbAccessToken() {
        String accessToken = AccessToken.getCurrentAccessToken().getToken();
        return accessToken;
    }
}
