package com.example.dreamtale.ui.login;

import static com.example.dreamtale.utils.AuthUtils.validatePass;
import static com.example.dreamtale.utils.AuthUtils.validatePhone;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.utils.DeviceUtils;
import com.example.dreamtale.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SignupFragment extends BaseFragment<LoginPresenter, LoginActivity> implements LoginView{

    @BindView(R.id.edt_pass)
    protected EditText edtPass;
    @BindView(R.id.edt_phone)
    protected EditText edtPhone;
    @BindView(R.id.btn_signup)
    protected Button btnSignup;
    @BindView(R.id.txt_login)
    protected TextView txtLogin;
    @BindView(R.id.btn_login_fb)
    protected Button btnSignupFb;
    @BindView(R.id.btn_login_gg)
    protected Button btnSignupGg;
    @BindView(R.id.container_edt_phone)
    protected LinearLayout containerEdtPhone;
    @BindView(R.id.container_edt_pass)
    protected LinearLayout containerEdtPass;
    @BindView(R.id.container_btn)
    protected LinearLayout containerButton;
    @BindView(R.id.rcy_category)
    protected RecyclerView rcyCategory;
    @BindView(R.id.layout_txt)
    protected LinearLayout layoutTxt;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_signup;
    }

    @Override
    public void onPrepareLayout() {
        setListener();
    }

    public void setListener() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("longtv", "onClick: " + btnSignup.getTag() );
                switch (btnSignup.getTag().toString()){
                    case "check_phone":
//                        if (validatePhone(edtPhone.getText().toString())) {
//                            getPresenter().checkPhoneNumber(edtPhone.getText().toString());
//                        } else {
//                            DialogUtils.showToastMessage("Phone number is invalid", getViewContext(), false);
//                        }
                        registerSuccess(new ArrayList<>());
                        break;
                    case "create_account":
                        DeviceInfo deviceInfo = DeviceUtils.getDeviceInfo(getViewContext());
                        AuthRequestBody authRequestBody = new AuthRequestBody(edtPhone.getText().toString(), edtPass.getText().toString(), deviceInfo);
                        getPresenter().doRegister(authRequestBody);
                        break;

                    case "create_name":
                        //TODO
                        break;

                }
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getViewContext().addFragment(R.id.frg_login, new LoginFragment(), true, LoginFragment.class.getSimpleName() );
            }
        });
    }


    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    public void loginSuccess(AuthRequestBody data) {

    }

    @Override
    public void loginFail(String message) {

    }

    @Override
    public void registerFail(String message) {

    }

    @Override
    public void isPhoneExist(String message) {
        DialogUtils.showToastMessage(message, getViewContext(), false);
    }

    @Override
    public void isPhoneNonExist() {
        btnSignup.setTag("create_account");
        containerEdtPhone.setVisibility(View.GONE);
        containerEdtPass.setVisibility(View.VISIBLE);
    }

    @Override
    public void registerSuccess(List<Category> data) {
        containerButton.setVisibility(View.GONE);
        layoutTxt.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getViewContext(), 2);
        Log.e("longtv", "registerSuccess:"  );
        rcyCategory.setVisibility(View.VISIBLE);
        rcyCategory.setLayoutManager(layoutManager);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());

        btnSignup.setTag("create_name");

        rcyCategory.setAdapter(new CategoryAdapter(categoryList, getViewContext()));

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

    }
}
