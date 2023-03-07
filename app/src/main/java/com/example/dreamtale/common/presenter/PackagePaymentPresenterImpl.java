package com.example.dreamtale.common.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BasePresenterImpl;
import com.example.dreamtale.common.view.PackagePaymentView;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.network.dto.Package;
import com.example.dreamtale.network.dto.PaymentRequest;
import com.example.dreamtale.network.dto.PaymentResponse;
import com.example.dreamtale.utils.DialogUtils;

import java.util.List;

public class PackagePaymentPresenterImpl extends BasePresenterImpl<PackagePaymentView> implements PackagePaymentPresenter {
    public PackagePaymentPresenterImpl(PackagePaymentView view) {
        super(view);
    }

    @Override
    public void getPackages() {
        DialogUtils.showProgressDialog(getViewContext());
        ServiceBuilder.getService().getPackages().enqueue(new BaseCallback<ContentDTO<Package>>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                DialogUtils.dismissProgressDialog(getViewContext());
                Toast.makeText(getViewContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ContentDTO<Package> data) {
                DialogUtils.dismissProgressDialog(getViewContext());
                mView.loadData(data.getContentList());
            }
        });
    }

//    @Override
//    public void createOrder(PaymentRequest paymentRequest) {
//        ServiceBuilder.getServicePayment().createOrder(paymentRequest).enqueue(new BaseCallback<PaymentResponse>() {
//            @Override
//            public void onError(String errorCode, String errorMessage) {
//
//            }
//
//            @Override
//            public void onResponse(PaymentResponse data) {
//                Log.e("longtv", "onResponse: order" + data.getDeepLink() );
//            }
//        });
//    }
}
