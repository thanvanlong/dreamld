package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthRequestBody implements Serializable {
    @SerializedName("phone")
    private String phone;
    @SerializedName("password")
    private String password;
    @SerializedName("deviceInfo")
    private DeviceInfo deviceInfo;
    @SerializedName("username")
    private String username;
    @SerializedName("state")
    private String state;
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("refreshToken")
    private String refreshToken;
    @SerializedName("avatar")
    private String avtUrl;

    public AuthRequestBody(String phoneNumber, String password, DeviceInfo deviceInfo) {
        this.phone = phoneNumber;
        this.password = password;
        this.deviceInfo = deviceInfo;
    }

    public AuthRequestBody(String phoneNumber, String password, String username) {
        this.phone = phoneNumber;
        this.password = password;
        this.username = username;
    }

    public AuthRequestBody(String phone, String password, DeviceInfo deviceInfo, String username) {
        this.phone = phone;
        this.password = password;
        this.deviceInfo = deviceInfo;
        this.username = username;
    }

    public AuthRequestBody() {
    }


    public String getAvtUrl() {
        return avtUrl;
    }

    public void setAvtUrl(String avtUrl) {
        this.avtUrl = avtUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "AuthRequestBody{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", deviceInfo=" + deviceInfo +
                ", username='" + username + '\'' +
                ", state='" + state + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
