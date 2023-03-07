package com.example.dreamtale.common.fragment;

import android.os.Build;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.adater.PackagePaymentAdapter;
import com.example.dreamtale.common.presenter.PackagePaymentPresenter;
import com.example.dreamtale.common.presenter.PackagePaymentPresenterImpl;
import com.example.dreamtale.common.view.PackagePaymentView;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.Package;
import com.example.dreamtale.network.dto.PaymentRequest;
import com.example.dreamtale.network.dto.PaymentResponse;
import com.example.dreamtale.ui.home.HomeActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PackagePaymentFragment extends BaseFragment<PackagePaymentPresenter, HomeActivity> implements PackagePaymentView {
    private List<Package> packageList = new ArrayList<>();
    @BindView(R.id.rcy_package)
    RecyclerView rcyPackage;
    PackagePaymentAdapter adapter;
    private Package aPackage;
    private static PackagePaymentFragment instance;
    private long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public static PackagePaymentFragment getInstance() {
        return instance;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_buy_package;
    }

    @Override
    public void onPrepareLayout() {
        instance = this;
        loadData();
    }

    private void loadData() {
        getPresenter().getPackages();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (HomeActivity.getInstance().isVerifyMomoPayment()) {
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setOrderId(orderId);
            paymentRequest.setPackageId(aPackage.getId());
            paymentRequest.setPaymentType("MOMO");
            ServiceBuilder.getService().verifyOrder(paymentRequest).enqueue(new BaseCallback<PaymentResponse>() {
                @Override
                public void onError(String errorCode, String errorMessage) {
                    Log.e("longtv", "onError: " + errorMessage );
                }

                @Override
                public void onResponse(PaymentResponse data) {
                    Log.e("longtv", "onResponse: payment");
                }
            });
        }
        Log.e("longtv", "onResume: package on resume" );
    }

    @Override
    public PackagePaymentPresenter onCreatePresenter() {
        return new PackagePaymentPresenterImpl(this);
    }

    @Override
    public void loadData(List<Package> data) {
        packageList.addAll(data);
        if (adapter == null) {
            setAdapter();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new PackagePaymentAdapter(packageList, getViewContext());
            rcyPackage.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
            rcyPackage.setAdapter(adapter);
        }
    }
}
