package com.example.dreamtale.ui.login;

import android.content.Intent;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.base.BaseView;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.network.dto.CategoryDTO;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.utils.AuthUtils;
import com.example.dreamtale.utils.DeviceUtils;
import com.example.dreamtale.utils.DialogUtils;
import com.example.dreamtale.utils.PrefManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class LoginFragment extends BaseFragment<LoginPresenter, LoginActivity> implements LoginView {
    @BindView(R.id.edt_phone)
    protected EditText edtPhone;
    @BindView(R.id.edt_pass)
    protected EditText edtPass;
    @BindView(R.id.btn_login)
    protected Button btnLogin;
    @BindView(R.id.txt_create_acc)
    protected TextView txtCreateAccount;
    @BindView(R.id.btn_login_gg)
    protected Button btnLoginGg;
    @BindView(R.id.btn_login_fb)
    protected Button btnLoginFb;
    @BindView(R.id.txt_forgot_pass)
    protected TextView txtForgotPass;
    @BindView(R.id.img_show_pass)
    protected ImageView imgShowPass;

    private boolean isShowPass = false;

    private static LoginFragment mInstance;

    public static LoginFragment getmInstance() {
        return mInstance;
    }

    public static synchronized  LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        mInstance = loginFragment;
        return loginFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onPrepareLayout() {
        setListener();
        mInstance = this;
    }


    public void setListener() {
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBaseActivity().addFragment(R.id.frg_login, new SignupFragment(), true, SplashFragmentLoginContent.class.getSimpleName());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceInfo deviceInfo = DeviceUtils.getDeviceInfo(getViewContext());
                AuthRequestBody authRequestBody = new AuthRequestBody(edtPhone.getText().toString(), edtPass.getText().toString(), deviceInfo);
//                if (!AuthUtils.validatePhoneAndPass(authRequestBody)) {
//                    DialogUtils.showToastMessage("Phone number or password is invalid", getViewContext(), false);
//                    return;
//                }
                getPresenter().doLogin(authRequestBody);
//                gotoHome();
            }
        });

        btnLoginGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO login with gg
            }
        });

        btnLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO login with fb
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO forgot pass
            }
        });
        imgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowPass = !isShowPass;
                if (isShowPass) {
                    imgShowPass.setImageResource(R.drawable.ic_show_pass);
                    edtPass.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    edtPass.setTransformationMethod(null);
                    imgShowPass.setImageResource(R.drawable.ic_hide_pass);
                }
            }
        });
    }

    @OnFocusChange(R.id.edt_pass)
    public void edtPassFocusChange() {
        if (!edtPass.isFocusable()) {
            getViewContext().hideSoftKeyboard();
        }
    }

    @OnTextChanged(R.id.edt_pass)
    public void edtPassChange() {
        if (!edtPass.getText().toString().matches("")) {
            imgShowPass.setVisibility(View.VISIBLE);
        } else {
            imgShowPass.setVisibility(View.GONE);
        }
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    public void loginSuccess(AuthRequestBody data) {
        PrefManager.setLogin(getViewContext(), true);
        gotoHome();
    }

    private void gotoHome() {
        Intent intent = new Intent(getBaseActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getBaseActivity().finish();
        getViewContext().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void loginFail(String message) {

    }

    @Override
    public void registerFail(String message) {
        DialogUtils.showToastMessage(message, getViewContext(), false);
    }

    @Override
    public void isPhoneExist(String message) {

    }

    @Override
    public void isPhoneNonExist() {

    }

    @Override
    public void getListCategorySuccess(ContentDTO<Category> categoryDTO) {

    }


    @Override
    public void registerSuccess(List<Category> data) {

    }

    @Override
    public void onGetOtpSuccess(int timeCountdown) {

    }

    @Override
    public void onGetOtpFail() {

    }

    @Override
    public void onChangePasswordSuccess() {

    }

    @Override
    public void onLimitedDevice() {

    }

    @Override
    public void showListDevices(List<DeviceInfo> data) {
        DeviceDialogFragment deviceDialogFragment = new DeviceDialogFragment();
        deviceDialogFragment.setListener(new DeviceDialogFragment.DialogDeviceListener() {
            @Override
            public void onRemoveSuccess() {
                getPresenter().doLogin(new AuthRequestBody(edtPhone.getText().toString(), edtPass.getText().toString(),
                        DeviceUtils.getDeviceInfo(getViewContext())));
                deviceDialogFragment.dismiss();
            }
        });
        deviceDialogFragment.init(getViewContext(), data, PrefManager.getAccessToken(getViewContext()));
        deviceDialogFragment.show(getViewContext().getSupportFragmentManager(), "Dialog");
    }

}
