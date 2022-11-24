package com.example.dreamtale.ui.login;

import com.example.dreamtale.base.BasePresenter;
import com.example.dreamtale.network.dto.AuthRequestBody;

public interface LoginPresenter extends BasePresenter {
    void doLogin(AuthRequestBody requestBody);
    void doRegister(AuthRequestBody requestBody);
    void checkPhoneNumber(String phoneNumber);
    void getListCategory(int size, int currentPage);
//    void doAutoLogin(DeviceInfoBody requestBody);
//    void checkExistPassword (String msisdn);
//    void getOtp(String msisdn);
}
