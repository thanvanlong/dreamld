package com.example.dreamtale.ui.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDialogFragment extends DialogFragment {
    @BindView(R.id.img_close_dialog)
    protected ImageView imgCloseDialog;
    @BindView(R.id.txt_select_all)
    protected TextView txtSelectAll;
    @BindView(R.id.rcy_device_logged)
    protected RecyclerView recyclerView;
    @BindView(R.id.btn_logout_device)
    protected Button btnLogoutDevice;
    private Context mContext;
    private List<DeviceInfo> deviceInfoList;
    private String accessToken;
    private DeviceInfoAdapter deviceInfoAdapter;
    private RemoveDevicesListener listener;
    private boolean check = false;
    public void init(Context context ,List<DeviceInfo> list, String accessToken) {
        this.mContext = context;
        this.deviceInfoList = list;
        this.accessToken = accessToken;
    }

    public void setListener(RemoveDevicesListener removeDevicesListener) {
        this.listener = removeDevicesListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.fragment_devices_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        view.setClipToOutline(false);
        if (deviceInfoAdapter == null) {
            deviceInfoAdapter = new DeviceInfoAdapter(deviceInfoList);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(deviceInfoAdapter);

        imgCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceDialogFragment.this.dismiss();
            }
        });


        btnLogoutDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO logout devices
            }
        });

        txtSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    deviceInfoList.forEach(deviceInfo -> {
                        deviceInfo.setSelected(check);
                    });
                }
                deviceInfoAdapter.notifyDataSetChanged();
            }
        });

    }

    public interface RemoveDevicesListener {
        void onRemoveSuccess();
    }
}
