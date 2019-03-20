package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.presenter.LoginPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.ConstantManager;
import com.shoesshop.groupassignment.utils.FacebookCallBackData;
import com.shoesshop.groupassignment.utils.FacebookHelper;
import com.shoesshop.groupassignment.view.LoginView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private Button mBtnLoginByPhone;
    private String TAG = "LoginActivity";
    private CallbackManager mCallbackManager;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intialView();
    }

    public static void intentToLoginActivitiy(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    private void intialView() {
        mBtnLoginByPhone = findViewById(R.id.button_login_by_phone);
        mBtnLoginByPhone.setOnClickListener(this);

        mLoginPresenter = new LoginPresenter(LoginActivity.this, LoginActivity.this, getApplication());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login_by_phone:
                loginByPhoneNumber();
                break;
        }
    }

    private void loginByPhoneNumber() {
        Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, ConstantManager.REQUEST_CODE_ACCOUNT_KIT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  ConstantManager.REQUEST_CODE_ACCOUNT_KIT) {
            final AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (result.getError() != null) {
                Log.e(TAG, result.getError().getErrorType().getMessage());
                return;
            } else if (result.wasCancelled()) {

            } else {
                FacebookHelper.handleAccountKitLogin(LoginActivity.this, result.getAccessToken().getToken(), new FacebookCallBackData() {
                    @Override
                    public void onSuccess(boolean isLogged) {
                        if (isLogged) {
                            mLoginPresenter.loginByPhone(result.getAccessToken().getToken());
                        }else{
                            showLoginFailedDialog();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        Log.e(TAG, message);
                    }
                });
            }
        } else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void loginByPhoneSuccess(Customer customer) {
        mLoginPresenter.addCustomer(customer);
    }

    @Override
    public void loginByPhoneFailed(String message) {
        Log.e(TAG, message);
    }

    @Override
    public void addCustomerSuccess(Customer customer) {
        if (customer.isFirstLogin() == true) {
            FirstLoginActivity.intentToFirstLoginActivitiy(LoginActivity.this);
        } else {
            HomeActivity.intentToHomeActivitiy(LoginActivity.this);
        }
    }

    private void showLoginFailedDialog() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        LayoutInflater layoutInflater = LoginActivity.this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Đăng nhập thất bại");
        txtSubInfo.setText("Xin hãy kiểm tra lại kết nối");
        viewLine.setVisibility(View.VISIBLE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Thử lại");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager()
                    .getPackageInfo("com.shoesshop.groupassignment",
                            PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
