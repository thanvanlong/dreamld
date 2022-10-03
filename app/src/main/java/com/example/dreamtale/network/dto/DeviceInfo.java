package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeviceInfo implements Serializable {
    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("id")
    private String id;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("osVersion")
    private String osVersion;

    @SerializedName("deviceName")
    private String deviceName;

    private boolean selected = false;

    public DeviceInfo(String deviceId, String deviceType, String osVersion, String deviceName) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.osVersion = osVersion;
        this.deviceName = deviceName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "deviceId='" + deviceId + '\'' +
                ", id='" + id + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
