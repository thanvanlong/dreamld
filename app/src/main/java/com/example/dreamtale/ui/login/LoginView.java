package com.example.dreamtale.ui.login;

import com.example.dreamtale.base.BaseView;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.network.dto.CategoryDTO;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.network.dto.DeviceInfo;

import java.util.List;

public interface LoginView extends BaseView<LoginPresenter> {
    void loginSuccess(AuthRequestBody data);
    void loginFail(String message);
    void registerFail(String message);
    void isPhoneExist(String message);
    void isPhoneNonExist();
    void getListCategorySuccess(ContentDTO<Category> categoryDTO);
    void registerSuccess(List<Category> data);
    void onGetOtpSuccess(int timeCountdown);
    void onGetOtpFail();
    void onChangePasswordSuccess();

    void onLimitedDevice();
    void showListDevices(List<DeviceInfo> data);
}
