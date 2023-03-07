package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class PaymentRequest {
    @SerializedName("orderId")
    private long orderId;
    @SerializedName("orderInfo")
    private String orderInfo;
    @SerializedName("requestId")
    private long requestId;
    @SerializedName("amount")
    private long amount;
    @SerializedName("packageId")
    private long packageId;
    @SerializedName("paymentType")
    private String paymentType;
    @SerializedName("paymentDate")
    private LocalDateTime localDateTime;


    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public PaymentRequest() {
    }

    public PaymentRequest(long orderId, String orderInfo, long requestId, long amount) {
        this.orderId = orderId;
        this.orderInfo = orderInfo;
        this.requestId = requestId;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
