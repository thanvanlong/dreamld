package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

public class PaymentResponse {
    @SerializedName("deeplink")
    private String deepLink;

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }
}
