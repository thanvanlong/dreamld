package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseDTO<T> implements Serializable {
    @SerializedName("data")
    private T data;
    @SerializedName("errorCode")
    private String errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
