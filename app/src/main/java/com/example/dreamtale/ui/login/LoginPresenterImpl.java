package com.example.dreamtale.ui.login;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BasePresenterImpl;
import com.example.dreamtale.common.dialog.YesNoDialog;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.network.dto.ResponseDTO;
import com.example.dreamtale.utils.DeviceUtils;
import com.example.dreamtale.utils.DialogUtils;
import com.example.dreamtale.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter{
    public LoginPresenterImpl(LoginView view) {
        super(view);
    }

    @Override
    public void doLogin(AuthRequestBody requestBody) {
        DialogUtils.showProgressDialog(getViewContext());
        ServiceBuilder.getService().login(requestBody).enqueue(new BaseCallback<AuthRequestBody>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                mView.loginFail(errorMessage);
                DialogUtils.dismissProgressDialog(getViewContext());
                Log.d("longtv", "onError: " + errorMessage);
                Toast.makeText(getViewContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(AuthRequestBody data) {
                DialogUtils.dismissProgressDialog(getViewContext());
                if (data != null) {
                    mView.loginSuccess(data);
                }
                PrefManager.saveAccessTokenInfo(getViewContext(), data.getAccessToken());
                PrefManager.saveRefreshTokenInfo(getViewContext(), data.getRefreshToken());
            }

            @Override
            public void onFailure(Call<ResponseDTO<AuthRequestBody>> call, Throwable t) {
                super.onFailure(call, t);
            }

            @Override
            protected void onLimitedDevice(AuthRequestBody body, String message) {
                super.onLimitedDevice(body, message);
                DialogUtils.dismissProgressDialog(getViewContext());
                Log.d("longtv", "onLimitedDevice: " + body.getAccessToken());
                PrefManager.saveAccessTokenInfo(getViewContext(), body.getAccessToken());
                YesNoDialog yesNoDialog = new YesNoDialog();
                yesNoDialog.setListener(new YesNoDialog.ItemClickListener() {
                    @Override
                    public void btnYesClick() {
                        yesNoDialog.dismiss();
                        Log.d("longtv", "btnYesClick: " + PrefManager.getAccessToken(getViewContext()));
                        getListDevice();
                    }

                    @Override
                    public void btnNoClick() {
                        yesNoDialog.dismiss();
                    }
                });
                yesNoDialog.init(getViewContext(), message);
                yesNoDialog.show(getViewContext().getSupportFragmentManager(), LoginPresenterImpl.class.getSimpleName());

            }
        });
    }

    @Override
    public void doRegister(AuthRequestBody requestBody) {
        DialogUtils.showProgressDialog(getViewContext());
        ServiceBuilder.getService().register(requestBody).enqueue(new BaseCallback<AuthRequestBody>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                mView.loginFail(errorMessage);
                DialogUtils.dismissProgressDialog(getViewContext());
                Log.d("longtv", "onError: " + errorMessage);
            }

            @Override
            public void onResponse(AuthRequestBody data) {
                String m_androidId = Settings.Secure.getString(getViewContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                DeviceInfo deviceInfo = DeviceUtils.getDeviceInfo(getViewContext());
                deviceInfo.setDeviceId(m_androidId);
                data.setDeviceInfo(deviceInfo);
                doLogin(data);
                DialogUtils.dismissProgressDialog(getViewContext());
                Log.d("longtv", "onResponse: register" + data.toString());
            }

        });
    }

    @Override
    public void checkPhoneNumber(String phoneNumber) {
        DialogUtils.showProgressDialog(getViewContext());
        ServiceBuilder.getService().checkPhoneNumber(phoneNumber).enqueue(new BaseCallback<AuthRequestBody>() {

            @Override
            public void onError(String errorCode, String errorMessage) {
                Log.d("longtv", "onError: " + errorMessage);
            }

            @Override
            public void onResponse(AuthRequestBody data) {

            }

            @Override
            protected void onAccountExist(String message) {
                super.onAccountExist(message);
                mView.isPhoneExist(message);
                DialogUtils.dismissProgressDialog(getViewContext());
            }
        });
    }

    private void getListDevice() {
        DialogUtils.showProgressDialog(getViewContext());
        ServiceBuilder.getService().getListDeviceLogged().enqueue(new BaseCallback<List<DeviceInfo>>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                //TODO handle error
            }

            @Override
            public void onResponse(List<DeviceInfo> data) {
                DialogUtils.dismissProgressDialog(getViewContext());
                Log.e("longtv", "onResponse: limit device " + data.size() );
                mView.showListDevices(data);
            }
        });
    }
}
