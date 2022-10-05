package com.example.dreamtale.ui.login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.network.dto.DeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceInfoAdapter extends RecyclerView.Adapter<DeviceInfoAdapter.ViewHolder> {
    private List<DeviceInfo> deviceInfoList;

    public DeviceInfoAdapter(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_logged, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeviceInfo deviceInfo = deviceInfoList.get(position);
        holder.imgTypeDevice.setImageResource(deviceInfo.getDeviceType().equalsIgnoreCase("Phone")
                ? R.drawable.ic_baseline_phone_iphone_24 : R.drawable.ic_baseline_tablet_mac_24);
        holder.txtTypeDevice.setText(deviceInfo.getDeviceName());
        if (deviceInfo.isSelected()) {
            holder.imgDeviceSelect.setImageResource(R.drawable.ic_device_selected);
        } else {
            holder.imgDeviceSelect.setImageResource(R.drawable.ic_device_not_select);
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceInfo.setSelected(!deviceInfo.isSelected());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (deviceInfoList != null) {
            return  deviceInfoList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_type_device)
        protected ImageView imgTypeDevice;
        @BindView(R.id.txt_type_device)
        protected TextView txtTypeDevice;
        @BindView(R.id.txt_time_expire)
        protected TextView txtTimeExpire;
        @BindView(R.id.img_device_select)
        protected ImageView imgDeviceSelect;
        @BindView(R.id.layout_root)
        protected RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
