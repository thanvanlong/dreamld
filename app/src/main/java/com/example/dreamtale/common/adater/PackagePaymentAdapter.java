package com.example.dreamtale.common.adater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.common.fragment.PackagePaymentFragment;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.Package;
import com.example.dreamtale.network.dto.PaymentRequest;
import com.example.dreamtale.network.dto.PaymentResponse;
import com.example.dreamtale.utils.DialogUtils;
import com.example.dreamtale.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackagePaymentAdapter extends RecyclerView.Adapter<PackagePaymentAdapter.ViewHolder>{

    private List<Package> packageList;
    private Context context;

    public PackagePaymentAdapter(List<Package> packageList, Context context) {
        this.packageList = packageList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy_package, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Package packages = packageList.get(position);
        ImageUtils.loadImageCorner(context, holder.ivPackage, packages.getImage());

        holder.ctnPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showProgressDialog((Activity) context);
                long orderId = System.currentTimeMillis() + packages.getId();
                PackagePaymentFragment.getInstance().setaPackage(packages);
                PackagePaymentFragment.getInstance().setOrderId(orderId);
                PaymentRequest paymentRequest = new PaymentRequest(orderId, packages.getName(), orderId, packages.getPrice());
                ServiceBuilder.getServicePayment().createOrder(paymentRequest).enqueue(new BaseCallback<PaymentResponse>() {
                    @Override
                    public void onError(String errorCode, String errorMessage) {
                        Log.e("longtv", "onError: order create" + errorMessage );
                    }

                    @Override
                    public void onResponse(PaymentResponse data) {
                        DialogUtils.dismissProgressDialog((Activity) context);
                        Log.e("longtv", "onResponse: order: " + data.getDeepLink() );
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                                Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.setData(Uri.parse(data.getDeepLink()));
                        context.startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_package)
        ImageView ivPackage;
        @BindView(R.id.ctn_package)
        LinearLayout ctnPackage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
